package com.java.crownlu.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by crownlu on 17/1/11.
 */
public class Client {

    public static void main(String [] args) throws Exception {
        Context context = new InitialContext();
        Server.IService service = (Server.IService) context.lookup("rmi://127.0.0.1:8811/service");

        Map<String, String> c = new HashMap<>();
        c.put("hello", "ss");
        System.out.println(service.service("hello", c));
    }
}
