package com.ascending.blair.service.jms;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class MessageSQSService {
    AmazonSQS sqs;

    public MessageSQSService(AmazonSQS sqs){
        this.sqs = sqs;
    }

    public void sendMessage(String queueUrl, String messageBody){
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
    }
}
