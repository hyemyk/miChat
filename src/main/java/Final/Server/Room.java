package Final.Server;

import java.util.List;
import java.util.Vector;

public class Room {

    public RoomManager roomManager;
    public String id;
    public int index;
    public String roomName;
    public List<Server.Client> clients = new Vector<>();
    public String chatRoomStatus;

    public Room() {//매개값 없는 룸 생성

    }

    public Room(
            RoomManager roomManager,
            String id,
            int index,
            String roomName,
            Server.Client client) {
        this.roomManager = roomManager;
        this.id = id;
        this.index = index;
        this.roomName = roomName;
        this.clients.add(client);
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
            //chatRoomStatus();
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


    public String chatRoomStatus() {
        String roomStatus = "";
        if (this.clients.size() > 0) {
            roomStatus += String.format("{\"roomName\":\"%s\", \"clientSize\":\"%s\"},", this.roomName, this.clients.size());
            System.out.println("this.clients.size() : " + this.clients.size());
            roomStatus = roomStatus.substring(0, roomStatus.length() - 1);
        }
        System.out.println("[채팅서버-roomStatus] " + roomStatus);
        return roomStatus;
    }
}
