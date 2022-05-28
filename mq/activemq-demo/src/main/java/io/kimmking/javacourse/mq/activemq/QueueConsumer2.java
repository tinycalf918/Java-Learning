package io.kimmking.javacourse.mq.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class QueueConsumer2 {

    public static void main(String[] args) throws JMSException {

        Destination destination = new ActiveMQQueue("test-queue");

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        ActiveMQConnection connection = (ActiveMQConnection) factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(destination);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {

                    System.out.println("consumer2 => receive from " + destination.toString() + ": " + message);

                } catch (Exception e) {
                    e.printStackTrace(); // 不要吞任何这里的异常，
                }
            }
        });

    }
}
