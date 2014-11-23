package server;

import server.impl.ServerApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Justin on 23.11.2014.
 */
public class ServerConsole {

    protected ServerApplication serverApplication = null;

    public ServerConsole (ServerApplication serverApplication) {
        this.serverApplication = serverApplication;
    }

    public void run () {
        System.out.println("Start client application console.");
        System.out.println("quit to close the application.");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String line = reader.readLine();

                if (line.equals("quit") || line.equalsIgnoreCase("quit")) {
                    this.serverApplication.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
