package antasmes.tech.demo.config;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Configuration
public class SSHConnect {

    SSHLogin sLogin;

    private Session session;
    private ChannelExec channel;

    @Bean
    @Autowired
    public int setup(SSHLogin sshLogin) throws Exception {
        try {
            session = new JSch().getSession(
                    "mongo_user",
                    "192.168.0.27",
                    sshLogin.getPort());
            session.setPassword("mongodb");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("echo 'Hello Ubuntu'");
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            String responseString = new String(responseStream.toByteArray());
            System.out.println(responseString);
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
        return 1;

    }

    private void init() throws JSchException {
        session.connect();
        channel.connect();
    }
}
