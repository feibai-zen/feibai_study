package com.feibai.spring.study.test.mock;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(8080);
      while (true) {
        Socket socket = serverSocket.accept();
        if (socket != null && socket.isConnected()) {
          new Thread(new Listener(socket)).start();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
