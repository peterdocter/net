package com.java.crownlu.activemq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by crownlu on 17/1/17.
 */
public class Consumer implements MessageListener {
    private Connection connection;
    private Destination destination;
    private Session session;
    private MessageConsumer consumer;

    public void init() throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("mytest");
        consumer = session.createConsumer(destination);
        consumer.setMessageListener(this);
    }

    @Override
    public void onMessage(Message message) {
        System.out.println(message);
    }

    public static void main(String [] args) throws Exception {
        Consumer consumer = new Consumer();
        consumer.init();
    }
}
