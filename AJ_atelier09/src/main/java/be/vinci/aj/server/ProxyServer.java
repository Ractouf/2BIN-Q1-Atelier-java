package be.vinci.aj.server;

import be.vinci.aj.domaine.QueryFactory;

import java.util.Scanner;

public class ProxyServer {
    Scanner scanner = new Scanner(System.in);

    public void startServer () {
        while (true) {
            String url = scanner.nextLine();
            new QueryHandler(new QueryFactory().getQuery()).start();
        }
    }
}
