# CS557: Assignment 1 - Multi-threaded HTTP Server

## Name: Kenneth Peter Fernandes

---
## Description:
- This application is a simple multi-threaded HTTP server that only accepts HTTP GET requests and returns the desired content to the client.
- With the help of Socket programming, the HTTP server application is build where the server listens to a port number.
-  With the ip address and the port number of the server, the client can request any resource hosted by the server by establishing connection first and then accessing the resource.
- The server application uses BufferedReader to read the incoming request from the client for a resource, PrintWriter is a character output stream to write the Response Header back to the client and BufferedOutputStream for sending binary output of the requested resource.
- With each connection to the client, a Thread instance is created where we read the request from the client and write the response header and requested resource in the form of binary output data. 
- The program is executed on remote.cs.binghamton.edu.
- The code for executing the program lies under the folder path "cs457-557-fall2020-pa1-kenneth-fernandes/httpServerMultiThread/src"
- The folder for storing the html, image and other resource files lies under the folder path "cs457-557-fall2020-pa1-kenneth-fernandes/httpServerMultiThread/www/".
- The application uses Ant for building and executing the program.

---

## References:
1. Create a simple HTTP Web Server in Java | Sylvain Saurel:
https://medium.com/@ssaurel/create-a-simple-http-web-server-in-java-3fc12b29d5fd

2. How to create HTTP Server in Java - ServerSocket Example:
https://javarevisited.blogspot.com/2015/06/how-to-create-http-server-in-java-serversocket-example.html

3. Class Files | java.nio.file.Files - probeContentType() | Oracle docs
https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html#probeContentType%28java.nio.file.Path%29

4. Get the Mime Type from a File:
https://www.rgagnon.com/javadetails/java-0487.html

5. Getting Date in HTTP format in Java:
https://stackoverflow.com/questions/7707555/getting-date-in-http-format-in-java

---

## Instruction to add files to the server:
- Navigate to the folder cs457-557-fall2020-pa1-kenneth-fernandes/httpServerMultiThread/www/.
- The folder "www" is used to add resource files.
---

Following are the commands and the instructions to run ANT on your project.

#### Note: build.xml is present in httpServerMultiThread/src folder.

---

## Instruction to clean:

```commandline
- Navigate to the repository folder:
cd cs457-557-fall2020-pa1-kenneth-fernandes

- Then run the command:
ant -buildfile httpServerMultiThread/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

---

## Instruction to compile:

```commandline
- Navigate to the repository folder:
cd cs457-557-fall2020-pa1-kenneth-fernandes

- Then run the command:
ant -buildfile httpServerMultiThread/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

---

## Instruction to run:

#### Use the below command to run the program. Please add the port number.

```commandline
- Navigate to the repository folder:
cd cs457-557-fall2020-pa1-kenneth-fernandes

- Then run the command:
ant -buildfile httpServerMultiThread/src/build.xml run-server
```
---

## Output of server console:

#### The application was executed on the remote.cs.binghamton.edu server.

```commandline

kferna11@remote06:~/Assignment/IDS/cs457-557-fall2020-pa1-kenneth-fernandes-master$ ant -buildfile httpServerMultiThread/src/build.xml run-server
Buildfile: /import/linux/home1/kferna11/Assignment/IDS/cs457-557-fall2020-pa1-kenneth-fernandes-master/httpServerMultiThread/src/build.xml

server-jar:
    [mkdir] Created dir: /import/linux/home1/kferna11/Assignment/IDS/cs457-557-fall2020-pa1-kenneth-fernandes-master/httpServerMultiThread/src/BUILD/jar
      [jar] Building jar: /import/linux/home1/kferna11/Assignment/IDS/cs457-557-fall2020-pa1-kenneth-fernandes-master/httpServerMultiThread/src/BUILD/jar/server.jar

run-server:
     [java] HTTP Server started. URL: 128.226.114.206:8080/
     [java]
     [java] /index.html|128.226.114.201|45018|1
     [java] /iron-man.png|128.226.114.202|48602|1
     [java] /eye-img.jpeg|128.226.114.203|56620|1
     [java] /iron-man.png|128.226.114.202|48960|2
     [java] /index.html|128.226.114.202|48972|2
     [java] /iron-man.png|128.226.114.203|56622|3
     [java] /index.html|128.226.114.203|56624|3
     [java] /index.html|128.226.114.201|45022|4


```
---

## Output of client console:

#### The command wget was executed on the remote.cs.binghamton.edu server.

```commandline
1. remote01.cs.binghamton.edu server:

kferna11@remote01:~$ wget 128.226.114.206:8080/
--2020-09-30 21:42:17--  http://128.226.114.206:8080/
Connecting to 128.226.114.206:8080... connected.
HTTP request sent, awaiting response... 200 OK
Length: 178 [text/html]
Saving to: ‘index.html.3’

index.html.3                          100%[=========================================================================>]     178  --.-KB/s    in 0s

2020-09-30 21:42:17 (25.5 MB/s) - ‘index.html.3’ saved [178/178]

kferna11@remote01:~$ wget 128.226.114.206:8080/
--2020-09-30 21:47:15--  http://128.226.114.206:8080/
Connecting to 128.226.114.206:8080... connected.
HTTP request sent, awaiting response... 200 OK
Length: 178 [text/html]
Saving to: ‘index.html.6’

index.html.6                          100%[=========================================================================>]     178  --.-KB/s    in 0s

2020-09-30 21:47:15 (24.6 MB/s) - ‘index.html.6’ saved [178/178]

2. remote02.cs.binghamton.edu server:

kferna11@remote02:~$ wget 128.226.114.206:8080/iron-man.png
--2020-09-30 21:44:08--  http://128.226.114.206:8080/iron-man.png
Connecting to 128.226.114.206:8080... connected.
HTTP request sent, awaiting response... 200 OK
Length: 105060 (103K) [image/png]
Saving to: ‘iron-man.png’

iron-man.png                          100%[=========================================================================>] 102.60K  --.-KB/s    in 0s

2020-09-30 21:44:08 (325 MB/s) - ‘iron-man.png’ saved [105060/105060]

kferna11@remote02:~$ wget 128.226.114.206:8080/iron-man.png
--2020-09-30 21:45:43--  http://128.226.114.206:8080/iron-man.png
Connecting to 128.226.114.206:8080... connected.
HTTP request sent, awaiting response... 200 OK
Length: 105060 (103K) [image/png]
Saving to: ‘iron-man.png.1’

iron-man.png.1                        100%[=========================================================================>] 102.60K  --.-KB/s    in 0s

2020-09-30 21:45:43 (449 MB/s) - ‘iron-man.png.1’ saved [105060/105060]

kferna11@remote02:~$ wget 128.226.114.206:8080
--2020-09-30 21:46:03--  http://128.226.114.206:8080/
Connecting to 128.226.114.206:8080... connected.
HTTP request sent, awaiting response... 200 OK
Length: 178 [text/html]
Saving to: ‘index.html.4’

index.html.4                          100%[=========================================================================>]     178  --.-KB/s    in 0s

2020-09-30 21:46:03 (24.4 MB/s) - ‘index.html.4’ saved [178/178]

3. remote03.cs.binghamton.edu server:

kferna11@remote03:~$ wget 128.226.114.206:8080/eye-img.jpeg
--2020-09-30 21:45:25--  http://128.226.114.206:8080/eye-img.jpeg
Connecting to 128.226.114.206:8080... connected.
HTTP request sent, awaiting response... 200 OK
Length: 80221 (78K) [image/jpeg]
Saving to: ‘eye-img.jpeg’

eye-img.jpeg                          100%[=========================================================================>]  78.34K  --.-KB/s    in 0s

2020-09-30 21:45:25 (163 MB/s) - ‘eye-img.jpeg’ saved [80221/80221]

kferna11@remote03:~$ wget 128.226.114.206:8080/iron-man.png
--2020-09-30 21:46:25--  http://128.226.114.206:8080/iron-man.png
Connecting to 128.226.114.206:8080... connected.
HTTP request sent, awaiting response... 200 OK
Length: 105060 (103K) [image/png]
Saving to: ‘iron-man.png.2’

iron-man.png.2                        100%[=========================================================================>] 102.60K  --.-KB/s    in 0s

2020-09-30 21:46:25 (209 MB/s) - ‘iron-man.png.2’ saved [105060/105060]

kferna11@remote03:~$ wget 128.226.114.206:8080/
--2020-09-30 21:46:58--  http://128.226.114.206:8080/
Connecting to 128.226.114.206:8080... connected.
HTTP request sent, awaiting response... 200 OK
Length: 178 [text/html]
Saving to: ‘index.html.5’

index.html.5                          100%[=========================================================================>]     178  --.-KB/s    in 0s

2020-09-30 21:46:58 (23.1 MB/s) - ‘index.html.5’ saved [178/178]

kferna11@remote03:~$ wget 128.226.114.206:8080/iron-man1.png
--2020-09-30 21:50:48--  http://128.226.114.206:8080/iron-man1.png
Connecting to 128.226.114.206:8080... connected.
HTTP request sent, awaiting response... 404 Not Found
2020-09-30 21:50:48 ERROR 404: Not Found.

```
---


### Academic Honesty statement:

---

"I have done this assignment completely on my own. I have not copied it, nor 
have I given my solution to anyone else. I understand that if I am involved in 
plagiarism or cheating I will have to sign an official form that I have cheated 
and that this form will be stored in my official university record. I also 
understand that I will receive a grade of 0 for the involved assignment and 
my grade will be reduced by one level (e.g., from A to A- or from B+ to B) 
for my first offense, and that I will receive a grade of “F” for the course 
for any additional offense of any kind."

Date: [09/30/2020]
