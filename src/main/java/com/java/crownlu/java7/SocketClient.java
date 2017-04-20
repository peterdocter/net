package com.java.crownlu.java7;

import com.java.crownlu.wnys.AESUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by crownlu on 17/3/8.
 */
public class SocketClient {

    public static void main(String [] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8881);
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        out.println("mytest");
        out.println("mytestww");
        out.flush();
//        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String l;
        while ((l = in.readLine()) != null) {
            AESUtils.p(l);
        }


    }
}
