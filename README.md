# CS557: Assignment 1

## Name: Kenneth Peter Fernandes

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

#### Use the below command to run the program.

```commandline
- Navigate to the repository folder:
cd cs457-557-fall2020-pa1-kenneth-fernandes

- Then run the command:
ant -buildfile httpServerMultiThread/src/build.xml run-server -Darg0=5000
```
---

## Output of server console:

```commandline

```
---

## Instruction to add files to the server:
- Navigate to the folder cs457-557-fall2020-pa1-kenneth-fernandes/httpServerMultiThread/www/.
- The folder "www" is used to add resource files.
---

## Description:
- This application is a simple multi-threaded HTTP server that only accepts HTTP GET requests and returns the desired content to the client.

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

### Academic Honesty statement:

---

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [09/30/2020]
