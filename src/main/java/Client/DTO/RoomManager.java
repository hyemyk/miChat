package Client.DTO;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    private static ArrayList<ChatRoomDTO> roomList; // 방의 리스트

    static {
        roomList = new ArrayList<ChatRoomDTO>();
    }

    public RoomManager() {

    }

    /**
     * 빈 룸을 생성
     * return ChatRoom
     */
    public ChatRoomDTO createRoom(String roomName, ArrayList<String> inviteeList) { // 룸을 새로 생성
        ChatRoomDTO room = new ChatRoomDTO(roomName);
        roomList.add(room);
        System.out.println("Room Created!");
        return room;
    }

    /**
     * 유저 리스트로 방을 생성
     *  users 입장시킬 유저 리스트
     *  GameRoom
     */
    public static ChatRoomDTO createRoom(List users) {

        room.enterUser(users);

        roomList.add(room);
        System.out.println("Room Created!");
        return room;
    }

    public static ChatRoomDTO getRoom(ChatRoomDTO chatRoom){

        int idx = roomList.indexOf(chatRoom);

        if(idx > 0){
            return roomList.get(idx);
        }
        else{
            return null;
        }
    }

    /**
     * 전달받은 룸을 제거
     * room 제거할 룸
     */
    public static void removeRoom(ChatRoomDTO room) {
        room.close();
        roomList.remove(room); // 전달받은 룸을 제거한다.
        System.out.println("Room Deleted!");
    }

    /**
     * 방의 현재 크기를 리턴
     * return 현재 size
     */
    public static int roomCount() {
        return roomList.size();
    }
}
