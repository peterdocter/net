package com.java.crownlu.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.net.Socket;

/**
 * Created by crownlu on 17/3/8.
 */
public class SimpleSocketConnectionPool {

    static class SocketPoolFactory extends BasePooledObjectFactory<Socket> {

        @Override
        public Socket create() throws Exception {
            return null;
        }

        @Override
        public PooledObject wrap(Socket obj) {
            return new DefaultPooledObject<>(obj);
        }
    }
}
