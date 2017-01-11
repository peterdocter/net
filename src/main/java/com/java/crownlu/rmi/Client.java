package com.java.crownlu.rmi;

import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by crownlu on 17/1/11.
 */
public class Client {

    public static void main(String [] args) throws Exception {
        Server.IService service = (Server.IService) Naming.lookup("rmi://localhost:8888/service02");

        Map<String, String> c = new HashMap<>();
        c.put("hello", "ss");
        System.out.println(service.service("hello", c));
    }
}
