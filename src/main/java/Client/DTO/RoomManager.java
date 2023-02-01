package Client.DTO;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    public static ArrayList<ChatRoomDTO> roomList; // 방의 리스트

    static {
        roomList = new ArrayList<ChatRoomDTO>();
    }

    public RoomManager() {
        System.out.println("이거 지울 것");

    }

    /**
     * 빈 룸을 생성
     * return ChatRoom
     */
    public ChatRoomDTO createRoom(String roomName, ArrayList<String> inviteeList) { // 룸을 새로 생성
        ChatRoomDTO room = new ChatRoomDTO(roomName);
        roomList.add(room);
        userDTO.setRoomList(roomList);
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
