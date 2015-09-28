package server;

import handler.ChatHandler;
import io.grpc.ServerImpl;
import io.grpc.netty.NettyServerBuilder;
import service.ChatServiceGrpc;

/**
 * Created by gilang on 28/09/2015.
 */
public class ChatServer {

    public static final int DEFAULT_PORT = 9090;

    public static void main(String[] args) {
        Runnable simple = ChatServer::simple;
        new Thread(simple).start();
    }

    public static void simple() {
        try {
            ServerImpl gRpcServer = NettyServerBuilder.forPort(DEFAULT_PORT)
                    .addService(ChatServiceGrpc.bindService(new ChatHandler()))
                    .build()
                    .start();
            System.out.println("Starting the chat server...");
            gRpcServer.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
