package com.apress.ejb3.chapter05.client;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

public class StatusMailerClient
{
  public StatusMailerClient() {
  }

  @Resource(mappedName = "StatusMessageTopicConnectionFactory")
  private TopicConnectionFactory statusMessageTopicCF;

  @Resource(mappedName = "StatusMessageTopic")
  private Topic statusTopic;

  public static void main(String[] args) {
    StatusMailerClient statusMailerClient = new StatusMailerClient();
    statusMailerClient.doIt();
  }

  private void doIt() {
    String from = "raghu.kodali@oracle.com";
    String to = "raghu.kodali@oracle.com";
    String content = 
      "Your order has been processed " + "If you have questions" + 
      " call EJB3 Application with order id # " + "1234567890";
    try {
      System.out.println("Before status TopicCF connection");
      Connection connection = 
        statusMessageTopicCF.createConnection("guest", "guest");
      // statusMessageTopicCF.createConnection()
      System.out.println("Created connection");
      connection.start();
      System.out.println("statted connection");
      System.out.println("Starting Topic Session");
      Session topicSession = 
        connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      MessageProducer publisher = topicSession.createProducer(statusTopic);
      System.out.println("created producer");
      Message message = topicSession.createMessage();
      message.setJMSType("MailMessage");

      message.setStringProperty("from", from);
      message.setStringProperty("to", to);
      message.setStringProperty("subject", "Status of your wine order");
      message.setStringProperty("content", content);
      System.out.println("before send");
      publisher.send(message);
      System.out.println("after send");
    }
    catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
