package server.processor;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import server.content.ContentProperties;

public class HttpRequestProcessor implements Runnable {

    private Socket clientSocket;
    private BufferedReader inputReader;
    private PrintWriter headerWriter;
    private BufferedOutputStream outputDataStream;
    private List<String> reqInputTokenLst;
    private String reqFilePath;
    private String requestInput;
    private File reqFile;
    private FileInputStream fileInputStream;
    private Map<String, Integer> fileAccesCountMap;
   

    private final String WWW_ROOT_DIRECTORY = "./www";
    private final String INDEX_FILE_PATH = "/index.html";
    private final String ERROR_FILE_PATH = "/error.html";

    public HttpRequestProcessor(Socket clientSocket, Map<String, Integer> fileAccesCountMap2) {
        this.clientSocket = clientSocket;
        this.fileAccesCountMap = fileAccesCountMap2;
    }

    @Override
    public void run() {
        try {
            

            inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            headerWriter = new PrintWriter(clientSocket.getOutputStream());

            outputDataStream = new BufferedOutputStream(clientSocket.getOutputStream());

            requestInput = inputReader.readLine();

            reqInputTokenLst = Arrays.asList(requestInput.split("\\s"));

            reqFilePath = reqInputTokenLst.get(1).equals("/") ? WWW_ROOT_DIRECTORY + INDEX_FILE_PATH
                    : WWW_ROOT_DIRECTORY + reqInputTokenLst.get(1);

            reqFile = new File(reqFilePath);

            writeOutput(!reqFile.exists());

        } catch (IOException e) {
            System.out.println("Server Error: Error in I/O processing");
        } finally {
            try {
                inputReader.close();
                headerWriter.close();
                outputDataStream.close();
                clientSocket.close();

            } catch (IOException e) {
                System.out.println("Server error: Error in I/O processing");
            }
        }

    }

    public void writeOutput(boolean isError) throws IOException {

        if (isError) {
            reqFilePath = WWW_ROOT_DIRECTORY + ERROR_FILE_PATH;
            reqFile = new File(reqFilePath);
        }

        ContentProperties contentProps = new ContentProperties(reqInputTokenLst.get(2), reqFile, isError);
        contentProps.updateContentProperties();

        headerWriter.print(contentProps.getContentPropertiesString());
        headerWriter.println();
        headerWriter.flush();

        outputDataStream.write(readFileInBytes(reqFile), 0, (int) reqFile.length());
        outputDataStream.flush();

        printClientRequestDetails(isError);

    }

    public byte[] readFileInBytes(File file) throws IOException {
        byte[] fd = new byte[(int) file.length()];
        fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            try {
                fileInputStream.read(fd);
                fileInputStream.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return fd;

    }

    public void printClientRequestDetails(boolean isError) {

        if (!isError) {
            String path = reqInputTokenLst.get(1).equals("/") ? INDEX_FILE_PATH : reqInputTokenLst.get(1);
            if (fileAccesCountMap.get(path) == null) {

                fileAccesCountMap.put(path, 1);

            } else {

                fileAccesCountMap.put(path, fileAccesCountMap.get(path) + 1);

            }

            System.out.println(path + "|" + clientSocket.getInetAddress().getHostAddress() + "|"
                    + clientSocket.getPort() + "|" + fileAccesCountMap.get(path));

        }

    }
}
