package server.content;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class ContentProperties {
    private String protocol;
    private boolean isError;
    private String status;
    private File file;
    private String date;
    private String server;
    private String lastModified;
    private String contentType;
    private String contentLength;

    public ContentProperties(String protocol, File file, boolean isError) {
        this.protocol = protocol;
        this.file = file;
        this.isError = isError;
    }

    public void updateContentProperties() throws IOException {

        date = "Date: " + (new Date()).toString() + "\n";
        server = "Server: Java HTTP Server" + "\n";
        if (isError) {
            status = protocol + " 404 Not Found" + "\n";
            lastModified = "";
            contentType = "";
            contentLength = "";
        } else {
            status = protocol + " 200 OK" + "\n";
            lastModified = "Last-Modified: " + new Date(file.lastModified()) + "\n";
            contentType = "Content-Type: " + Files.probeContentType(Paths.get(file.getCanonicalPath())) + "\n";
            contentLength = "Content-Length: " + file.length() + "\n";
        }
    }

    public String getContentPropertiesString() {
        return status + date + server + lastModified + contentType + contentLength;
    }
}
