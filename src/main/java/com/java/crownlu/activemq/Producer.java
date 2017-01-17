package com.java.crownlu.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by crownlu on 17/1/16.
 */
public class Producer {

    private Session session;
    private Connection connection;
    private Destination destination;
    private MessageProducer producer;

    public Producer init() throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, TopicSession.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("mytest");
        producer = session.createProducer(destination);
        return this;
    }

    public void send(String text) throws Exception {
        TextMessage message = session.createTextMessage(text);
        producer.send(message);
    }

    public void destory() throws Exception {
        session.close();
        connection.close();
    }

    public static void main(String [] args) throws Exception {
        Producer p = new Producer().init();
        p.send("mytest message");
        p.send("this is my second message");
    }
}
