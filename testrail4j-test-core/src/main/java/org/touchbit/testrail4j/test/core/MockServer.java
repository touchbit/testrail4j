/*
 * MIT License
 *
 * Copyright © 2019 TouchBIT.
 * Copyright © 2019 Oleg Shaburov.
 * Copyright © 2018 Maria Vasilenko.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.touchbit.testrail4j.test.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringJoiner;

public class MockServer {

    private final Logger log = LoggerFactory.getLogger(MockServer.class);

    public MockServer(int port) {
        Server server = new Server(port);
        Thread serverThread = new Thread(server);
        serverThread.start();
        log.info("Mock server started. Port: {}\n", port);
    }

    private class Server implements Runnable {

        private ServerSocket serverSocket;

        private Server(final int port) {
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                throw new SocketException("The socket is not available on the port " + port, e);
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Socket socket = serverSocket.accept();
                    SocketProcessor processor = new SocketProcessor(socket);
                    new Thread(processor).start();
                }
            } catch (Exception e) {
                throw new SocketException("The socket is not available", e);
            }
        }
    }

    @SuppressWarnings("unused")
    private class SocketProcessor implements Runnable {

        private final Socket socket;
        private String requestBody;

        private MockResponse mockResponse;

        private SocketProcessor(final Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            log.info("Initiated request processing");
            readRequest();
            writeResponse(mockResponse);
            log.info("Request processing completed");
        }

        private void writeResponse(MockResponse mockResponse) {
            final StringJoiner result = new StringJoiner("\r\n");
            try {
                final OutputStream outputStream = socket.getOutputStream();
                if (mockResponse == null) {
                    mockResponse = new MockResponse();
                }
                String responseBody = mockResponse.body == null ? "" : mockResponse.body;
                result.add("HTTP/1.1 " + mockResponse.code);
                mockResponse.headers.forEach((k, v)  -> result.add(k + ":" + v));
                result.add("Content-Length: " + (responseBody.length()));
                result.add("Connection: close");
                result.add("");
                result.add(responseBody);
                log.info("Response:\n{}\n", result);
                outputStream.write(result.toString().getBytes());
                outputStream.flush();
            } catch (IOException ignore) {
                log.info("Mock server stopped.");
            }
        }

        private void readRequest() {
            try {
                final InputStream inputStream = socket.getInputStream();
                final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                final StringJoiner joiner = new StringJoiner("\n", "", "\n");
                String line;
                while(!(line = reader.readLine()).equals("")) {
                    joiner.add(line);
                }
                requestBody = joiner.toString();
            } catch (IOException e) {
                log.error("Error reading request from client", e);
            }
        }

        public String getRequestBody() {
            return requestBody;
        }

        public void setRequestBody(String requestBody) {
            this.requestBody = requestBody;
        }

        public MockResponse getMockResponse() {
            return mockResponse;
        }

        public void setMockResponse(MockResponse mockResponse) {
            this.mockResponse = mockResponse;
        }

    }

}