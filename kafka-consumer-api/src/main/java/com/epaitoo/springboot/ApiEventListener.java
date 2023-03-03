package com.epaitoo.springboot;

public interface ApiEventListener {
    void onData(String event);
    void processComplete();
}
