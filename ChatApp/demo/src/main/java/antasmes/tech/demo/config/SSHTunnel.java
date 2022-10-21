package antasmes.tech.demo.config;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Configuration("ssh_connect")
public class SSHTunnel {

    private Session session;
    private ChannelExec channelExec;

    @Bean
    @Autowired
    public int init(SSHConfig sshConfig) throws Exception {
        String command = "echo Connected to the server";

        ByteArrayOutputStream responseStream = setup(sshConfig, command);

        if (responseStream == null) {
            throw new NullPointerException("There has been an error while getting the response stream");
        }

        while (channelExec.isConnected()) {
            Thread.sleep(100);
        }

        String response = new String(responseStream.toByteArray());
        System.out.println(response);

        return 1;
    }

    public void closeSession() {
        if (channelExec != null) {
            channelExec.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    private ByteArrayOutputStream setup(SSHConfig sshConfig, String command) {
        try {
            // SSH connection
            session = new JSch().getSession(
                    sshConfig.getUser(), // Connect as user
                    sshConfig.getHost(), // Connect to host
                    sshConfig.getRemotePort()); // Connection port
            session.setPassword(sshConfig.getPwd()); // User password
            session.setConfig("StrictHostKeyChecking", "no"); // Disables Strict key checking when connecting
            session.connect();

            channelExec = (ChannelExec) session.openChannel("exec");

            channelExec.setCommand(command);

            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channelExec.setOutputStream(responseStream); // Creates response for server command
            channelExec.connect();

            // Reroutes from given port 27018 to 27017 (MongoDB database port)
            session.setPortForwardingL(
                    sshConfig.getFromPort(),
                    sshConfig.getHost(),
                    sshConfig.getToPort());

            return responseStream;

        } catch (JSchException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ChannelExec getChannelExec() {
        return channelExec;
    }

    public void setChannelExec(ChannelExec channelExec) {
        this.channelExec = channelExec;
    }
}
