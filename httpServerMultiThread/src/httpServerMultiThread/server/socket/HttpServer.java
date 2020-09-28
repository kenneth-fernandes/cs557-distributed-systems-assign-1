package server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import server.processor.HttpRequestProcessor;

/**
 * HttpServer
 */
public class HttpServer {
    private ServerSocket server;
    private int port;

    public HttpServer(int port) throws IOException {

        server = new ServerSocket(port);
        this.port = port;
        System.out.println("HTTP Server started. URL: http://localhost:" + port + "/");
    }

    public void processRequest() throws IOException {

        while (true) {
            Thread thread = new Thread(new HttpRequestProcessor(server.accept()));
            thread.start();

        }
    }

}