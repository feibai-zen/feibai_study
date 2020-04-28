package com.feibai.study.demos.network.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * 非阻塞IO
 */
public class Demo01 {

  public static void main(String[] args) throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.bind(new InetSocketAddress(8888));
    serverSocketChannel.configureBlocking(false); //设置服务端操作都是非阻塞的

    Selector selector = Selector.open(); //选择器
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //对客户端的accept事件关心

    while (true) {
      selector.select(); //会阻塞住，直到有事件触发

      Set<SelectionKey> selectionKeys = selector.selectedKeys(); //看下有哪些事件被触发了
      System.out.println("selectionKeys:" + selectionKeys);

      Iterator<SelectionKey> iterator = selectionKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey key = iterator.next();

        if (key.isAcceptable()) {
          //客户端 accept被触发了
          ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();

          SocketChannel clientChannel = serverChannel.accept();
          System.out.println("channel is acceptable");
          clientChannel.configureBlocking(false);

          //客户端channel注册OP_WRITE事件
          clientChannel.register(selector, SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
          //客户端可以往里写数据了

          System.out.println("channel is writeable");
          String data = "hello world\n";

          //注意这里的是客户端的channel，因为是使用客户端channel注册OP_WRITE事件
          SocketChannel clientChannel = (SocketChannel) key.channel();
          ByteBuffer buffer = ByteBuffer.allocate(data.length());
          buffer.put(data.getBytes());
          buffer.flip();
          clientChannel.write(buffer);
        }
        key.cancel(); //取消事件
        iterator.remove();
      }
    }
  }
}
