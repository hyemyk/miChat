package ChatProgram.main;

import ChatProgram.ChatRoomPkg.ChatRoom;
import ChatProgram.ChatRoomPkg.User;
import ChatProgram.service.CommonUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class Client {
    AsynchronousSocketChannel socketChannel;
    Client(AsynchronousSocketChannel socketChannel) {

        this.socketChannel = socketChannel;
        //receive(); // 매개값으로 AsynchronousSocketChannel 필드 초기화 후
    }
    public static User socketConnect() {
        System.out.println("[클라이언트 시작]");
        User loginUserInfo = new User();

        try {

            //비동기 소켓 채널 생성
            AsynchronousSocketChannel asc = AsynchronousSocketChannel.open();
            loginUserInfo.setSocket(asc);
            //서버로 연결 요청하기
            asc.connect(new InetSocketAddress("localhost", 50001), null, new CompletionHandler<Void, Void>() {
                @Override
                public void completed(Void result, Void attachment) {
                    System.out.println("client 연결요청 completed thread: " + Thread.currentThread().getName());
                    //서버로 데이터 보내기
                    //receive(asc);
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    exc.printStackTrace();
                    try {
                        asc.close();
                    } catch (Exception e) {
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginUserInfo;
    }

    //서버로 데이터 보내기
    public static void receive(AsynchronousSocketChannel asc, String sendText, ChatRoom chatRoom) {
        ByteBuffer byteBuffer = CommonUtil.encode(sendText);
        asc.write(byteBuffer, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                System.out.println("데이터 보냄: " + sendText);
                //서버가 보낸 데이터 받기
                send(asc, chatRoom);

            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
                try {
                    asc.close();
                } catch (Exception e) {
                }
            }
        });
    }

    //서버가 보낸 데이터 받기
    public static void send(AsynchronousSocketChannel asc, ChatRoom chatRoom) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        asc.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                try {
                    attachment.flip();
                    Charset charset = Charset.forName("utf-8");
                    String receiveData = charset.decode(attachment).toString();
                    System.out.println("데이터 받음: " + receiveData);
                    chatRoom.setChatContent(receiveData);
                    //asc.close();
                } catch (Exception e) {
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
                try {
                    asc.close();
                } catch (Exception e) {
                }
            }
        });
    }
}
