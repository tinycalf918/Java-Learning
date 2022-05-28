package io.kimmking.javacourse.mq.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class QueueProducer {

    public static void main(String[] args) throws JMSException {

        Destination destination = new ActiveMQQueue("test-queue");

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        ActiveMQConnection connection = (ActiveMQConnection) factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);

        int index = 0;
        while (index++ < 100) {
            TextMessage message = session.createTextMessage(index + " message.");
            producer.send(message);
        }

        session.close();
        connection.close();

    }
}
