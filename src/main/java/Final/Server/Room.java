package Final.Server;

import java.util.List;
import java.util.Vector;

public class Room {

    public RoomManager roomManager;
    public String id;
    public int index;
    public String roomName;
    public List<Server.Client> clients;
    public String chatRoomStatus;

    public Room(){//매개값 없는 룸 생성

    }

    public Room(
            RoomManager roomManager,
            String id,
            int index,
            String roomName) {
        this.roomManager = roomManager;
        this.id = id;
        this.index = index;
        this.roomName = roomName;
        clients = new Vector<>();
        this.chatRoomStatus = "[]";
    }

    /**
     * [ Method :: entryRoom ]
     *
     * @DES :: 방 들어가기
     * @IP1 :: client {Client}
     */
    public void entryRoom(Server.Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
            client.userRooms.add(this);
            //client.room = this;
            chatRoomStatus();
        }
    }

    /**
     * [ Method :: leaveRoom ]
     *
     * @DES :: 방 나가기
     * @IP1 :: client {Client}
     * @S.E :: 만약 방에 아무도 없다면 삭제처리
     */
    public void leaveRoom(Server.Client client) {
        this.clients.remove(client);
        //client.room = null;
        client.userRooms.remove(this);
        if (this.clients.size() < 1) {
            roomManager.destroyRoom(this);
        }
    }



    void chatRoomStatus() {
        chatRoomStatus = "[";
        if(this.clients.size() > 0) {
            for (Server.Client client : clients) {
                chatRoomStatus += String.format("{\"clientSize\":\"%s\"},", this.clients.size());
                System.out.println("this.clients.size() : " + this.clients.size());
            }
            chatRoomStatus = chatRoomStatus.substring(0,chatRoomStatus.length()-1);
        }
        chatRoomStatus += "]";
        System.out.println("[채팅서버-chatRoomStatus] " + chatRoomStatus);
    }
}
