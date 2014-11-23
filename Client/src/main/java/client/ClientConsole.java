package client;

import client.impl.ClientApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Justin on 22.11.2014.
 */
public class ClientConsole {

    protected ClientApplication clientApplication = null;

    public ClientConsole (ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

    public void run () {
        System.out.println("Start client application console.");
        System.out.println("quit to close the application.");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String line = reader.readLine();

                if (line.equals("quit") || line.equalsIgnoreCase("quit")) {
                    this.clientApplication.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
