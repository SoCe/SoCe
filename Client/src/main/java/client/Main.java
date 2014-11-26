package client;

import client.impl.ClientApplication;
import module.impl.ModuleManagerImpl;
import org.apache.log4j.BasicConfigurator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Justin on 18.11.2014.
 */
public class Main {

    protected static int buildNumber = 1;

    public static void main (String[] args) {
        //Log4J
        BasicConfigurator.configure();

        ClientApplication clientApplication = new ClientApplication();
        Thread thread = new Thread(clientApplication);
        thread.start();

        //https://developers.google.com/protocol-buffers/docs/javatutorial

        //http://www.oracle.com/technetwork/server-storage/ts-4883-1-159000.pdf

        //http://www.torsten-horn.de/techdocs/maven.htm

        //http://www.tutorialspoint.com/design_pattern/

        //http://www.philipphauer.de/study/se/design-pattern.php

        ClientConsole clientConsole = new ClientConsole(clientApplication);
        clientConsole.run();
    }

}
