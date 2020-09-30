package server.socket;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import server.processor.HttpRequestProcessor;

/**
 * HttpServer
 */
public class HttpServer {
    private ServerSocket server;
    private Map<String, Integer> fileAccesCountMap = new HashMap<>();

    public HttpServer(int port) throws IOException {

        server = new ServerSocket(port);
        System.out.println(
                "HTTP Server started. URL: " + Inet4Address.getLocalHost().getHostAddress() + ":" + port + "/\n");
    }

    public void processRequest() throws IOException {
        while (true) {
            Socket client = server.accept();
            
            Thread thread = new Thread(new HttpRequestProcessor(client, fileAccesCountMap));
            thread.start();
        }
    }
}