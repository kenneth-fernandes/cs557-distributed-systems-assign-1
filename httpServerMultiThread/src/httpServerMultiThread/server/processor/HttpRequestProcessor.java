package server.processor;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import server.content.ContentProperties;

public class HttpRequestProcessor implements Runnable {

    private Socket clientSocket;
    private BufferedReader inputReader;
    private PrintWriter headerWriter;
    private BufferedOutputStream outputDataStream;
    private List<String> reqInputTokenLst;
    private String reqFilePath;
    private String method;
    private String requestInput;
    private File reqFile;
    private boolean isError = false;

    private final String WWW_ROOT_DIRECTORY = "./www";
    private final String INDEX_FILE_PATH = "/index.html";
    private final String ERROR_FILE_PATH = "/error.html";

    public HttpRequestProcessor(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // we read characters from the client via input stream on the socket
            inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // we get character output stream to client (for headers)
            headerWriter = new PrintWriter(clientSocket.getOutputStream());
            // get binary output stream to client (for requested data)
            outputDataStream = new BufferedOutputStream(clientSocket.getOutputStream());

            // get first line of the request from the client
            requestInput = inputReader.readLine();

            reqInputTokenLst = Arrays.asList(requestInput.split("\\s"));

            method = reqInputTokenLst.get(0);
            reqFilePath = reqInputTokenLst.get(1).equals("/") ? WWW_ROOT_DIRECTORY + INDEX_FILE_PATH
                    : WWW_ROOT_DIRECTORY + reqInputTokenLst.get(1);

            reqFile = new File(reqFilePath);
            isError = !reqFile.exists();

            if (isError) {
                reqFilePath = WWW_ROOT_DIRECTORY + ERROR_FILE_PATH;
                reqFile = new File(reqFilePath);
            }

            ContentProperties contentProps = new ContentProperties(reqInputTokenLst.get(2), reqFile, isError);
            contentProps.updateContentProperties();

            headerWriter.print(contentProps.getContentPropertiesString());
            headerWriter.println();
            headerWriter.flush();

            // ===================================================
            byte[] fd = new byte[(int) reqFile.length()];
            FileInputStream fin = new FileInputStream(reqFile);
            fin.read(fd);
            fin.close();

            // ===================================================

            outputDataStream.write(fd, 0, (int) reqFile.length());
            outputDataStream.flush();

        } catch (Exception e) {
        } finally {
            try {
                inputReader.close();
                headerWriter.close();
                outputDataStream.close();
                clientSocket.close();

            } catch (IOException e) {
                System.out.println("Server error: " + e.getMessage());
            }

        }

    }
}
