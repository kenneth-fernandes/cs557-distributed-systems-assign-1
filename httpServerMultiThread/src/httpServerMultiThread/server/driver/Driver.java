package server.driver;

import java.io.IOException;

import server.socket.HttpServer;

public class Driver {
    public static void main(String[] args) throws NumberFormatException, IOException {
        if (args.length != 1 || args[0].equals("${arg0}")) {
            System.err.println("Error: Incorrect number of arguments. Program accepts 2 argumnets.");
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