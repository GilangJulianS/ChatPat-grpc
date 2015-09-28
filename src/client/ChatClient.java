package client;

import io.grpc.ChannelImpl;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import model.Status;
import model.User;
import service.ChatServiceGrpc;
import service.ChatServiceGrpc.*;
import service.Chatservice;
import service.Chatservice.*;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by gilang on 28/09/2015.
 */
public class ChatClient {

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 9090;

    private ChannelImpl channel;
    private ChatServiceBlockingStub blockingStub;
    private ChatServiceStub asyncStub;

    public ChatClient(){
        channel = NettyChannelBuilder.forAddress(DEFAULT_HOST, DEFAULT_PORT)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        blockingStub = ChatServiceGrpc.newBlockingStub(channel);
        asyncStub = ChatServiceGrpc.newStub(channel);
    }

    public ChatClient(String host, int port){
        channel = NettyChannelBuilder.forAddress(host, port)
                    .negotiationType(NegotiationType.PLAINTEXT)
                    .build();
        blockingStub = ChatServiceGrpc.newBlockingStub(channel);
        asyncStub = ChatServiceGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException{
        channel.shutdown().awaitTermination(3, TimeUnit.SECONDS);
    }

    public int join(int userId, String channelName) {
        UserChannel param = UserChannel.newBuilder()
                                .setUserId(userId)
                                .setChannelName(channelName)
                                .build();
        MyInteger retval = blockingStub.join(param);
        return retval.getValue();
    }

    public int leave(int userId, String channelName){
        UserChannel param = UserChannel.newBuilder()
                .setUserId(userId)
                .setChannelName(channelName)
                .build();
        MyInteger retval = blockingStub.leave(param);
        return retval.getValue();
    }

    public String getMessage(int userId) {
        MyInteger param = MyInteger.newBuilder()
                            .setValue(userId)
                            .build();
        MyString retval = blockingStub.getMessage(param);
        return retval.getValue();
    }

    public int sendMessage(int userId, String channelName, String message) {
        ChatMessage param = ChatMessage.newBuilder()
                                .setUserId(userId)
                                .setChannelName(channelName)
                                .setMessage(message)
                                .build();
        MyInteger retval = blockingStub.sendMessage(param);
        return retval.getValue();
    }

    public int createUser(String nick) {
        MyString param = MyString.newBuilder()
                            .setValue(nick)
                            .build();
        MyInteger retval = blockingStub.createUser(param);
        return retval.getValue();
    }

    public int deleteUser(int userId) {
        MyInteger param = MyInteger.newBuilder()
                            .setValue(userId)
                            .build();
        MyInteger retval = blockingStub.deleteUser(param);
        return retval.getValue();
    }

    public static void main(String[] args){
        ChatClient client = new ChatClient();
        Scanner scanner = new Scanner(System.in);
        boolean exit=false;
        int nick=-1;
        long fetch_interval=1000;
        class FetchTask extends TimerTask {
            private ChatClient client;
            private int nick;
            public FetchTask(ChatClient in_client, int in_nick){
                client=in_client;nick=in_nick;
            }
            public void run() {
                try{
                    //fetch message
                    for (String str:client.getMessage(nick).split("///")){
                        if (str.length()>0){
                            String[] stp=str.split("\\|\\|");
                            System.out.println("["+stp[1]+"] ("+stp[0]+") "+stp[2]);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        Timer timer=new Timer();

        while (!exit){
            try {
                String input = scanner.nextLine();
                if (input.equals("/exit")) {
                    if (client.deleteUser(nick) == Status.SUCCESS) {
                        exit = true;
                    }
                } else if (input.equals("/nick")) {
                    if (nick != -1) client.deleteUser(nick);
                    String new_nick = "";
                    nick = client.createUser(new_nick);
                } else if (input.startsWith("/nick ")) {
                    if (nick != -1) client.deleteUser(nick);
                    String new_nick = input.split(" ")[1];
                    nick = client.createUser(new_nick);
                } else if (input.startsWith("/join ")) {
                    String channel = input.split(" ")[1];
                    int res = client.join(nick, channel);
                    if (res == Status.SUCCESS) {
                        //        			System.out.println(nick+" joined channel |"+channel+"|");
                    } else if (res == Status.FAIL) {
                        System.out.println("You already joined channel " + channel);
                    }
                } else if (input.startsWith("/leave ")) {
                    String channel = input.split(" ")[1];
                    int res = client.leave(nick, channel);
                    if (res == Status.SUCCESS) {
                        //        			System.out.println(nick+" left channel |"+channel+"|");
                    } else if (res == Status.FAIL) {
                        System.out.println("You haven't joined channel " + channel);
                    } else if (res == Status.NOT_FOUND) {
                        System.out.println("Channel " + channel + " doesn't exist");
                    }
                } else if (input.startsWith("@") && input.contains(" ")) {
                    String[] in_ar = input.split(" ", 2);
                    String channel = in_ar[0].substring(1);
                    String msg = in_ar[1];
                    int res = client.sendMessage(nick, channel, msg);
                    if (res == Status.SUCCESS) {
                        //        			System.out.println("["+channel+"] ("+nick+") |"+msg+"|");
                    } else if (res == Status.FORBIDDEN) {
                        System.out.println("You haven't joined to channel " + channel);
                    } else if (res == Status.NOT_FOUND) {
                        System.out.println("Channel " + channel + " doesn't exist");
                    } else if (res == Status.FAIL) {
                        System.out.println("Failed to send '" + msg + "' to channel " + channel);
                    }
                } else {
                    if (client.sendMessage(nick, "", input) == Status.SUCCESS) {
                        //        			System.out.println("[ALL] ("+nick+") |"+input+"|");
                    } else {
                        System.out.println("You haven't joined any channel yet");
                    }
                }
                if ((!exit) && (nick != -1)) {
                    timer.cancel();
                    timer.purge();
                    timer = new Timer();
                    timer.schedule(new FetchTask(client, nick), 0, fetch_interval);
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        timer.cancel();
        timer.purge();
        scanner.close();
        try {
            client.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Exit program");
    }
}
