package com.mxrph.service;

import java.util.List;

public interface MessageSenderService {
    void send(String phoneTo);
    void send(List<String> phonesTo);
}
