package server.content;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ContentProperties {
    private String protocol;
    private boolean isError;
    private File file;
    private String dateStr = "";
    private String status = "";
    private String server = "";
    private String lastModified = "";
    private String contentType = "";
    private String contentLength = "";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss O");

    public ContentProperties(String protocol, File file, boolean isError) {
        this.protocol = protocol;
        this.file = file;
        this.isError = isError;

    }

    public void updateContentProperties() throws IOException {

        dateStr = "Date: " + formatter.format(ZonedDateTime.ofInstant((new Date()).toInstant(), ZoneId.of("GMT")))
                + "\n";
        server = "Server: Java HTTP Server" + "\n";
        if (isError) {
            status = protocol + " 404 Not Found" + "\n";
        } else {
            status = protocol + " 200 OK" + "\n";
            lastModified = "Last-Modified: "
                    + formatter.format(
                            ZonedDateTime.ofInstant((new Date(file.lastModified())).toInstant(), ZoneId.of("GMT")))
                    + "\n";
            contentType = "Content-Type: " + Files.probeContentType(Paths.get(file.getCanonicalPath())) + "\n";
            contentLength = "Content-Length: " + file.length() + "\n";
        }
    }

    public String getContentPropertiesString() {
        return status + dateStr + server + lastModified + contentType + contentLength;
    }
}
