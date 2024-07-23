package com.mxrph.service.impl;

import com.mxrph.service.MessageSenderService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class MessageSenderServiceImpl implements MessageSenderService {
    @Value("${twilio.api.sid}")
    public String accountSID;
    @Value("${twilio.api.token}")
    public String authToken;

    @Override
    public void send(String phoneTo) {
        Twilio.init(accountSID, authToken);
        Message message = Message.creator(
                new PhoneNumber(phoneTo),
                new PhoneNumber("+13613065240"),
                "Test message")
                .create();

        System.out.println(message.getSid());
    }

    @Override
    public void send(List<String> phonesTo) {
        return;
    }
}