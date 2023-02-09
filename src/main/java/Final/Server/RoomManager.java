package Final.Server;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class RoomManager {

    public List<Server.Client> clients;    // 전체 Client
    public List<Room> rooms;        // 전체 Room
    public String roomStatus;       // 반환 Json -> String Room

    public RoomManager(List<Server.Client> clients) {
        this.clients = clients;
        this.rooms = new Vector<>();
        this.roomStatus = "[]";
    }

    /**
     * [ Method :: updateRoomStatus ]
     *
     * @DES :: Client에 전달할 텍스트 처리하는 함수
     * @S.E :: Json -> Text
     * */
    void updateRoomStatus() {
        // System.out.println("updateRoomStatus call");

        roomStatus = "[";
        if(rooms.size() > 0) {
            for (Room room : rooms) {
                roomStatus += String.format("{\"id\":\"%s\", \"roomName\":\"%s\",\"roomSize\":\"%s\"},", room.id, room.roomName, room.clients.size());
                System.out.println("room.clients.size() : " + room.clients.size());
            }
            roomStatus = roomStatus.substring(0,roomStatus.length()-1);
        }
        roomStatus += "]";
        System.out.println("[채팅서버] " + roomStatus);
    }

    /**
     * [ Method :: createRoom ]
     *
     * @DES :: 새로운 방생성 함수
     * @IP1 :: title {String}
     * @IP1 :: client {Client}
     * @S.E :: 모든 Client의 상태를 업데이트
     * */
    public Room createRoom(String roomName, Server.Client client) {
        System.out.println("roomName : " + roomName);
        System.out.println("client : " + client);
        System.out.println("createRoom실행함-1?");
        // ### unique id ###
        String uniqueID = UUID.randomUUID().toString();

        Room newRoom = new Room(this, uniqueID, rooms.size(), roomName);
        rooms.add(newRoom);
        System.out.println("createRoom실행함-2?");
        // #전달 - 모든 Client에게 상황보고
        updateRoomStatus();
        for (int i = 0; i < clients.size(); i++) { clients.get(i).sendRoomList(); }
        System.out.println("createRoom실행함-3?");
        // #입장
        newRoom.entryRoom(client);
        return newRoom;
    }

    /**
     * [ Method :: destroyRoom ]
     *
     * @DES :: 기존방 제거하는 함수
     * @IP1 :: room {Room}
     * @S.E :: 모든 Client의 상태를 업데이트
     * */
    public void destroyRoom(Room room) {
        rooms.remove(room);

        // #전달 - 모든 Client에게 상황보고
        updateRoomStatus();
        for (int i = 0; i < clients.size(); i++) { clients.get(i).sendRoomList(); }
    }

}
