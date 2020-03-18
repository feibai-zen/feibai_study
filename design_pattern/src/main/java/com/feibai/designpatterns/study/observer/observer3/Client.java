package com.feibai.designpatterns.study.observer.observer3;

public class Client{
    public static void main(String[] args) {

        NewsProvider provider = new NewsProvider();
        User user;
        for (int i = 0; i < 10; i++) {
            user = new User("user:"+i);
            provider.register(user);
        }

    }
}