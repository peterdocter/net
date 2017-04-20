package com.java.crownlu.java7;

import com.java.crownlu.wnys.AESUtils;

import java.io.*;
import java.net.ServerSocket;

/**
 * Created by crownlu on 17/3/8.
 */
public class SocketServer {
    public static void main(String [] a) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8881);

        while (true) {
            java.net.Socket socket = serverSocket.accept();
            new Thread(() -> {
                try (InputStream in = socket.getInputStream();
                     InputStreamReader reader = new InputStreamReader(in);
                     BufferedReader bufferedReader = new BufferedReader(reader);
                     ) {

                    String l = null;
                    while (true) {
                        AESUtils.p("prepre:");
                        l = bufferedReader.readLine();
                        if ("mytestww".equals(l)) {
                            break;
                        }
                        AESUtils.p("in:" + l);
                        AESUtils.p("------------------------");
                    }

                    AESUtils.p("----------1");
                    OutputStream out = socket.getOutputStream();
                    AESUtils.p("----------1");
                    PrintWriter o = new PrintWriter(out);
                    AESUtils.p("----------3");
                    o.println(":xxxxx" + Thread.currentThread());
                    AESUtils.p("----------4");
                    o.flush();
                    AESUtils.p("----------5");
                    Thread.sleep(800);
                    File f = new File("");
                    FileInputStream fileInputStream = new FileInputStream(f);

                    fileInputStream.getFD();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
