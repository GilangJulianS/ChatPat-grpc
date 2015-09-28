package handler;

import io.grpc.stub.StreamObserver;
import model.Channel;
import model.Status;
import model.User;
import service.ChatServiceGrpc;
import service.Chatservice.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gilang on 28/09/2015.
 */
public class ChatHandler implements ChatServiceGrpc.ChatService {

    List<User> users;
    List<Channel> channels;

    public ChatHandler(){
        users = new ArrayList<>();
        channels = new ArrayList<>();
    }

    @Override
    public void join(UserChannel request, StreamObserver<MyInteger> responseObserver) {
        User user = searchUser(request.getUserId());
        Channel channel = searchChannel(request.getChannelName());
        MyInteger retval = MyInteger.getDefaultInstance();
        if(user == null){
            retval = MyInteger.newBuilder().setValue(Status.NOT_FOUND).build();
        }else if(channel == null){
            channel = new Channel(request.getChannelName());
            channel.addUser(user);
            channels.add(channel);
            user.addPendingMessage("Admin||" + request.getChannelName() + "||You have joined this channel");
            for(User u : channel.getUsers()){
                if(u != user){
                    u.addPendingMessage("Admin||" + request.getChannelName() + "||" + user.getNick() + " has joined this channel");
                }
            }
            retval = MyInteger.newBuilder().setValue(Status.SUCCESS).build();
        }else if(channel.isUserExist(request.getUserId())){
//            user.addPendingMessage("Admin||" + channelName + "||You already joined this channel");
            retval = MyInteger.newBuilder().setValue(Status.FAIL).build();
        }else{
            channel.addUser(user);
            user.addPendingMessage("Admin||" + request.getChannelName() + "||You have joined this channel");
            for(User u : channel.getUsers()){
                if(u != user){
                    u.addPendingMessage("Admin||" + request.getChannelName() + "||" + user.getNick() + " has joined this channel");
                }
            }
            retval = MyInteger.newBuilder().setValue(Status.SUCCESS).build();
        }
        responseObserver.onValue(retval);
        responseObserver.onCompleted();
    }

    @Override
    public void leave(UserChannel request, StreamObserver<MyInteger> responseObserver) {
        Channel channel = searchChannel(request.getChannelName());
        MyInteger retval = MyInteger.getDefaultInstance();
        if(channel == null){
            retval = MyInteger.newBuilder().setValue(Status.NOT_FOUND).build();
        }else if(!channel.isUserExist(request.getUserId())){
//            searchUser(userId).addPendingMessage("Admin||" + channelName + "||You haven't joined this channel");
            retval = MyInteger.newBuilder().setValue(Status.FAIL).build();
        }else{
            User user = searchUser(request.getUserId());
            user.addPendingMessage("Admin||" + request.getChannelName() + "||You have left this channel");
            for(User u : channel.getUsers()){
                if(u != user){
                    u.addPendingMessage("Admin||" + request.getChannelName() + "||" + user.getNick() + " has left this channel");
                }
            }
            retval = MyInteger.newBuilder().setValue(channel.removeUser(request.getUserId())).build();
        }
        responseObserver.onValue(retval);
        responseObserver.onCompleted();
    }

    @Override
    public void getMessage(MyInteger request, StreamObserver<MyString> responseObserver) {
        User user = searchUser(request.getValue());
        String message = user.getPendingMessages();
        MyString retval = MyString.getDefaultInstance();
        if(message != null){
            retval = MyString.newBuilder().setValue(message).build();
        }
        responseObserver.onValue(retval);
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessage(ChatMessage request, StreamObserver<MyInteger> responseObserver) {
        User user = searchUser(request.getUserId());
        MyInteger retval = MyInteger.getDefaultInstance();
        if(user == null)
            retval = MyInteger.newBuilder().setValue(Status.NOT_FOUND).build();
        if(request.getMessage() == null || request.getMessage().equals(""))
            retval = MyInteger.newBuilder().setValue(Status.FAIL).build();
        if(request.getChannelName() == null || request.getChannelName().equals("")){
            boolean joinedAChannel = false;
            for(Channel channel : channels){
                if(channel.isUserExist(request.getUserId())){
                    joinedAChannel = true;
                    String newMessage = user.getNick() + "||" + channel.getName() + "||" + request.getMessage();
                    for(User u : channel.getUsers()){
                        u.addPendingMessage(newMessage);
                    }
                }
            }
            if(!joinedAChannel){
//                user.addPendingMessage("Admin|| ||You haven't joined any channel yet");
                retval = MyInteger.newBuilder().setValue(Status.FAIL).build();
            } else {
                retval = MyInteger.newBuilder().setValue(Status.SUCCESS).build();
            }
        }else{
            Channel channel = searchChannel(request.getChannelName());
            if (channel==null){
                retval = MyInteger.newBuilder().setValue(Status.NOT_FOUND).build();
            } else if(channel.isUserExist(request.getUserId())){
                String newMessage = user.getNick() + "||" + request.getChannelName() + "||" + request.getMessage();
                for(User u : channel.getUsers()){
                    u.addPendingMessage(newMessage);
                }
                retval = MyInteger.newBuilder().setValue(Status.SUCCESS).build();
            }else{
                retval = MyInteger.newBuilder().setValue(Status.FORBIDDEN).build();
            }
        }
        responseObserver.onValue(retval);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(MyString request, StreamObserver<MyInteger> responseObserver) {
        User user;
        MyInteger retval;
        if(request.getValue() == null || request.getValue().equals("")){
            user = new User();
            users.add(user);
        }else{
            user = new User(request.getValue());
            users.add(user);
        }
        user.addPendingMessage("Admin|| ||You have logged in as " + user.getNick());
        retval = MyInteger.newBuilder().setValue(user.getId()).build();
        responseObserver.onValue(retval);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(MyInteger request, StreamObserver<MyInteger> responseObserver) {
        boolean found = false;
        int i=0;
        MyInteger retval = MyInteger.getDefaultInstance();
        while(i<users.size() && !found){
            if(users.get(i).getId() == request.getValue()){
                users.remove(i);
                found = true;
            }
        }
        if(found){
            retval = MyInteger.newBuilder().setValue(Status.SUCCESS).build();
        } else {
            retval = MyInteger.newBuilder().setValue(Status.FAIL).build();
        }
        responseObserver.onValue(retval);
        responseObserver.onCompleted();
    }

    public Channel searchChannel(String channelName){
        Channel channel = null;
        for(int i=0; i<channels.size(); i++){
            if(channels.get(i).getName().equals(channelName)){
                channel = channels.get(i);
                break;
            }
        }
        return channel;
    }

    public User searchUser(int userId){
        User user = null;
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getId() == userId){
                user = users.get(i);
                break;
            }
        }
        return user;
    }
}
