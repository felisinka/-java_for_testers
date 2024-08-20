package ru.stqa.mantis.manager;


import java.io.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JamesCliHelper extends HelperBase{

    private Logger log = Logger.getGlobal();

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) {
        try {
            ProcessBuilder builder = new ProcessBuilder();

            builder.command(
                    "java", "-cp", "james-server-jpa-app.lib/*",
                    "org.apache.james.cli.ServerCmd", "AddUser", email,password,
                    "--username", "james-admin", "--password", "ha74254Trg");

            builder.directory(new File(System.getProperty("user.home") + "/Documents/autotests/james-server-jpa-guice"));
            Process process = builder.start();

            String result = new String(process.getInputStream().readAllBytes());
            log.log(Level.INFO, result);

            log.log(Level.WARNING, new String(process.getErrorStream().readAllBytes()));
        }
         catch (Throwable e) {
        System.out.println(e);
    }
    }

    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }
}
