package Client.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomManager {
    private static List<ChatRoomDTO> roomList; // 방의 리스트
    private static AtomicInteger atomicInteger;

    static {
        roomList = new ArrayList<ChatRoomDTO>();
        atomicInteger = new AtomicInteger();
    }

    public RoomManager() {

    }

    /**
     * 빈 룸을 생성
     * return ChatRoom
     */
    public static ChatRoomDTO createRoom() { // 룸을 새로 생성(빈 방)
        int roomId = atomicInteger.incrementAndGet();// room id 채번
        ChatRoomDTO room = new ChatRoomDTO(roomId);
        roomList.add(room);
        System.out.println("Room Created!");
        return room;
    }

    /**
     * 방을 생성함과 동시에 방장을 만들어줌
     * owner 방장
     * ChatRoom
     */
    public static ChatRoomDTO createRoom(UserDTO owner) { // 유저가 방을 생성할 때 사용(유저가 방장으로 들어감)
        int roomId = atomicInteger.incrementAndGet();// room id 채번

        ChatRoomDTO room = new ChatRoomDTO(roomId);
        room.enterUser(owner);
        room.setOwner(owner);

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
        int roomId = atomicInteger.incrementAndGet();// room id 채번

        ChatRoomDTO room = new ChatRoomDTO(roomId);
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