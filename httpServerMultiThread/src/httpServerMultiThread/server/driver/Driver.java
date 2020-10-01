package server.driver;

import java.io.IOException;

import server.socket.HttpServer;

public class Driver {
    public static void main(String[] args) {
        if (args.length != 1 || args[0].equals("${portNumber}")) {
            System.err.println("Error: Incorrect number of arguments. Program accepts 1 argumnets.");
            System.exit(0);
        }
        try {
            HttpServer server = new HttpServer(Integer.parseInt(args[0]));
            server.processRequest();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

    }
}