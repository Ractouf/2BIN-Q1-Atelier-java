package main;

import domain.Request;
import domain.RequestFactoryImpl;

public class Main {

    public static void main(String[] args) {
        Request request = new RequestFactoryImpl().getRequest();
        Server server = new Server(request);
        server.listenKeyboard();
    }
}
