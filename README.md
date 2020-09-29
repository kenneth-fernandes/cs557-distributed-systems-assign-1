ant -buildfile httpServerMultiThread/src/build.xml all

ant -buildfile httpServerMultiThread/src/build.xml run-server -Darg0=5000

References:
https://medium.com/@ssaurel/create-a-simple-http-web-server-in-java-3fc12b29d5fd
https://javarevisited.blogspot.com/2015/06/how-to-create-http-server-in-java-serversocket-example.html
https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html#probeContentType%28java.nio.file.Path%29
https://www.rgagnon.com/javadetails/java-0487.html