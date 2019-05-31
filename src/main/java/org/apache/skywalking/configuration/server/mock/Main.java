package org.apache.skywalking.configuration.server.mock;

import io.grpc.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ConfigServiceImpl service = new ConfigServiceImpl();
        Server start = ServerBuilder.forPort(9555).addService(service).build().start();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            switch (command.trim()) {
                case "exit":
                    System.exit(0);
                case "list":
                    System.out.println(service.list());
                    break;
                case "":
                    break;
                default:
                    if (command.startsWith("set ")) {
                        String[] parts = command.split(" ");
                        if (parts.length == 3) {
                            service.put(parts[1], parts[2]);
                            System.out.println("set done.");
                        }
                    } else if (command.startsWith("get ")) {
                        String[] parts = command.split(" ");
                        if (parts.length == 2) {
                            System.out.println(service.get(parts[1]));
                        }
                    }
            }
        }
    }
}
