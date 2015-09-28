package service;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;


@javax.annotation.Generated("by gRPC proto compiler")
public class ChatServiceGrpc {

  // Static method descriptors that strictly reflect the proto.
  public static final io.grpc.MethodDescriptor<Chatservice.UserChannel,
      Chatservice.MyInteger> METHOD_JOIN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "ChatService", "join",
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.UserChannel.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.MyInteger.parser()));
  public static final io.grpc.MethodDescriptor<Chatservice.UserChannel,
      Chatservice.MyInteger> METHOD_LEAVE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "ChatService", "leave",
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.UserChannel.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.MyInteger.parser()));
  public static final io.grpc.MethodDescriptor<Chatservice.MyInteger,
      Chatservice.MyString> METHOD_GET_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "ChatService", "getMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.MyInteger.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.MyString.parser()));
  public static final io.grpc.MethodDescriptor<Chatservice.ChatMessage,
      Chatservice.MyInteger> METHOD_SEND_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "ChatService", "sendMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.ChatMessage.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.MyInteger.parser()));
  public static final io.grpc.MethodDescriptor<Chatservice.MyString,
      Chatservice.MyInteger> METHOD_CREATE_USER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "ChatService", "createUser",
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.MyString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.MyInteger.parser()));
  public static final io.grpc.MethodDescriptor<Chatservice.MyInteger,
      Chatservice.MyInteger> METHOD_DELETE_USER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "ChatService", "deleteUser",
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.MyInteger.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(Chatservice.MyInteger.parser()));

  public static ChatServiceStub newStub(io.grpc.Channel channel) {
    return new ChatServiceStub(channel);
  }

  public static ChatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatServiceBlockingStub(channel);
  }

  public static ChatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatServiceFutureStub(channel);
  }

  public static interface ChatService {

    public void join(Chatservice.UserChannel request,
                     io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver);

    public void leave(Chatservice.UserChannel request,
                      io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver);

    public void getMessage(Chatservice.MyInteger request,
                           io.grpc.stub.StreamObserver<Chatservice.MyString> responseObserver);

    public void sendMessage(Chatservice.ChatMessage request,
                            io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver);

    public void createUser(Chatservice.MyString request,
                           io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver);

    public void deleteUser(Chatservice.MyInteger request,
                           io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver);
  }

  public static interface ChatServiceBlockingClient {

    public Chatservice.MyInteger join(Chatservice.UserChannel request);

    public Chatservice.MyInteger leave(Chatservice.UserChannel request);

    public Chatservice.MyString getMessage(Chatservice.MyInteger request);

    public Chatservice.MyInteger sendMessage(Chatservice.ChatMessage request);

    public Chatservice.MyInteger createUser(Chatservice.MyString request);

    public Chatservice.MyInteger deleteUser(Chatservice.MyInteger request);
  }

  public static interface ChatServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyInteger> join(
            Chatservice.UserChannel request);

    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyInteger> leave(
            Chatservice.UserChannel request);

    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyString> getMessage(
            Chatservice.MyInteger request);

    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyInteger> sendMessage(
            Chatservice.ChatMessage request);

    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyInteger> createUser(
            Chatservice.MyString request);

    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyInteger> deleteUser(
            Chatservice.MyInteger request);
  }

  public static class ChatServiceStub extends io.grpc.stub.AbstractStub<ChatServiceStub>
      implements ChatService {
    private ChatServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ChatServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceStub(channel, callOptions);
    }

    @Override
    public void join(Chatservice.UserChannel request,
        io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_JOIN, callOptions), request, responseObserver);
    }

    @Override
    public void leave(Chatservice.UserChannel request,
        io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_LEAVE, callOptions), request, responseObserver);
    }

    @Override
    public void getMessage(Chatservice.MyInteger request,
        io.grpc.stub.StreamObserver<Chatservice.MyString> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_GET_MESSAGE, callOptions), request, responseObserver);
    }

    @Override
    public void sendMessage(Chatservice.ChatMessage request,
        io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request, responseObserver);
    }

    @Override
    public void createUser(Chatservice.MyString request,
        io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_CREATE_USER, callOptions), request, responseObserver);
    }

    @Override
    public void deleteUser(Chatservice.MyInteger request,
        io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_DELETE_USER, callOptions), request, responseObserver);
    }
  }

  public static class ChatServiceBlockingStub extends io.grpc.stub.AbstractStub<ChatServiceBlockingStub>
      implements ChatServiceBlockingClient {
    private ChatServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ChatServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceBlockingStub(channel, callOptions);
    }

    @Override
    public Chatservice.MyInteger join(Chatservice.UserChannel request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_JOIN, callOptions), request);
    }

    @Override
    public Chatservice.MyInteger leave(Chatservice.UserChannel request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_LEAVE, callOptions), request);
    }

    @Override
    public Chatservice.MyString getMessage(Chatservice.MyInteger request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_GET_MESSAGE, callOptions), request);
    }

    @Override
    public Chatservice.MyInteger sendMessage(Chatservice.ChatMessage request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @Override
    public Chatservice.MyInteger createUser(Chatservice.MyString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_CREATE_USER, callOptions), request);
    }

    @Override
    public Chatservice.MyInteger deleteUser(Chatservice.MyInteger request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_DELETE_USER, callOptions), request);
    }
  }

  public static class ChatServiceFutureStub extends io.grpc.stub.AbstractStub<ChatServiceFutureStub>
      implements ChatServiceFutureClient {
    private ChatServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ChatServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceFutureStub(channel, callOptions);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyInteger> join(
        Chatservice.UserChannel request) {
      return futureUnaryCall(
          channel.newCall(METHOD_JOIN, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyInteger> leave(
        Chatservice.UserChannel request) {
      return futureUnaryCall(
          channel.newCall(METHOD_LEAVE, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyString> getMessage(
        Chatservice.MyInteger request) {
      return futureUnaryCall(
          channel.newCall(METHOD_GET_MESSAGE, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyInteger> sendMessage(
        Chatservice.ChatMessage request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyInteger> createUser(
        Chatservice.MyString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_CREATE_USER, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<Chatservice.MyInteger> deleteUser(
        Chatservice.MyInteger request) {
      return futureUnaryCall(
          channel.newCall(METHOD_DELETE_USER, callOptions), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final ChatService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder("ChatService")
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_JOIN,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                Chatservice.UserChannel,
                Chatservice.MyInteger>() {
              @Override
              public void invoke(
                  Chatservice.UserChannel request,
                  io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver) {
                serviceImpl.join(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_LEAVE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                Chatservice.UserChannel,
                Chatservice.MyInteger>() {
              @Override
              public void invoke(
                  Chatservice.UserChannel request,
                  io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver) {
                serviceImpl.leave(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_GET_MESSAGE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                Chatservice.MyInteger,
                Chatservice.MyString>() {
              @Override
              public void invoke(
                  Chatservice.MyInteger request,
                  io.grpc.stub.StreamObserver<Chatservice.MyString> responseObserver) {
                serviceImpl.getMessage(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SEND_MESSAGE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                Chatservice.ChatMessage,
                Chatservice.MyInteger>() {
              @Override
              public void invoke(
                  Chatservice.ChatMessage request,
                  io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver) {
                serviceImpl.sendMessage(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_CREATE_USER,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                Chatservice.MyString,
                Chatservice.MyInteger>() {
              @Override
              public void invoke(
                  Chatservice.MyString request,
                  io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver) {
                serviceImpl.createUser(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_DELETE_USER,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                Chatservice.MyInteger,
                Chatservice.MyInteger>() {
              @Override
              public void invoke(
                  Chatservice.MyInteger request,
                  io.grpc.stub.StreamObserver<Chatservice.MyInteger> responseObserver) {
                serviceImpl.deleteUser(request, responseObserver);
              }
            }))).build();
  }
}
