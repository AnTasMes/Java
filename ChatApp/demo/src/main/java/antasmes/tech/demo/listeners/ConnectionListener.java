package antasmes.tech.demo.listeners;

import java.time.LocalTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Session;

import antasmes.tech.demo.config.SSHTunnel;
import lombok.RequiredArgsConstructor;

@Component("connectionListener")
@RequiredArgsConstructor
public class ConnectionListener {

    private final SSHTunnel tunnel;
    private Thread validationThread;

    private int reloadTimeMilis = 500;

    public static Boolean isConnected = false;

    @Bean
    @DependsOn({ "sshinit" })
    public void init() {
        this.validationThread = new Thread(new ConnectionValidator());
        this.validationThread.start();
    }

    public void stop() {
        try {
            this.validationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class ConnectionValidator implements Runnable {

        @Override
        public void run() {
            Session session = tunnel.getSession();

            while (true) {
                if (session.isConnected()) {
                    // System.out.println("Session connected - " + LocalTime.now());

                    isConnected = true;

                } else {
                    System.out.println("Session disconnected - " + LocalTime.now());

                    isConnected = false;

                    retryConnection();
                }

                sleep(reloadTimeMilis);
            }
        }

        private void sleep(int millis) {
            try {
                Thread.sleep(reloadTimeMilis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void retryConnection() {
            tunnel.makeConnection("echo Validator connected");
        }
    }
}
