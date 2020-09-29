package server.socket;


import java.io.IOException;
import java.net.ServerSocket;

import server.processor.HttpRequestProcessor;

/**
 * HttpServer
 */
public class HttpServer {
    private ServerSocket server;

    public HttpServer(int port) throws IOException {

        server = new ServerSocket(port);
        System.out.println("HTTP Server started. URL: http://localhost:" + port + "/");
    }

    public void processRequest() throws IOException {
        while (true) {
            Thread thread = new Thread(new HttpRequestProcessor(server.accept()));
            thread.start();
        }
    }
}