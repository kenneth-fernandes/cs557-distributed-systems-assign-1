package server.processor;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class HttpRequestProcessor implements Runnable {

    private Socket clientSocket;
    private BufferedReader inputReader;
    private PrintWriter headerWriter;
    private BufferedOutputStream dataOut;

    public HttpRequestProcessor(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // we manage our particular client connection

        String fileRequested = null;
        try {
            // we read characters from the client via input stream on the socket
            inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // we get character output stream to client (for headers)
            headerWriter = new PrintWriter(clientSocket.getOutputStream());
            // get binary output stream to client (for requested data)
            dataOut = new BufferedOutputStream(clientSocket.getOutputStream());

            // get first line of the request from the client
            String requestInput = inputReader.readLine();

            List<String> reqInputTokenLst = Arrays.asList(requestInput.split("\\s"));

            String method = reqInputTokenLst.get(0);
            String reqFilePath = reqInputTokenLst.get(1).equals("/") ? "/index.html" : reqInputTokenLst.get(1);
            System.out.println(requestInput);

        } catch (Exception e) {
        }

    }
}
