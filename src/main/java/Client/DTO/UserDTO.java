package Client.DTO;

import Client.controller.ChatRoomController;

import java.net.Socket;

public class UserDTO {
    private int id; 			// Unique ID
    private ChatRoomDTO room; 		// 유저가 속한 룸이다.
    private Socket sock; 		// 소켓 object
    private String nickName;	// 닉네임


    public UserDTO() { // 아무런 정보가 없는 깡통 유저를 만들 때
    }

    /**
     * 유저 생성
     *nickName 닉네임
     */
    public UserDTO(String nickName) { // 닉네임 정보만 가지고 생성
        this.nickName = nickName;
    }


    /**
     * 방에 입장시킴
     * room  입장할 방
     */
    public void enterRoom(ChatRoomDTO room) {
        room.enterUser(this); // 룸에 입장시킨 후
        this.room = room; // 유저가 속한 방을 룸으로 변경한다.(중요)
    }

    /**
     * 방에서 퇴장
     * room 퇴장할 방
     */
    public void exitRoom(ChatRoomDTO room){
        this.room = null;
        // 퇴장처리(화면에 메세지를 준다는 등)
        // ...
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChatRoomDTO getRoom() {
        return room;
    }

    public void setRoom(ChatRoomDTO room) {
        this.room = room;
    }

    public Socket getSock() {
        return sock;
    }

    public void setSock(Socket sock) {
        this.sock = sock;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }



    /*
        equals와 hashCode를 override 해줘야, 동일유저를 비교할 수 있다
        비교할 때 -> gameUser 간 equals 비교, list에서 find 등
     */
 /*   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameUser gameUser = (GameUser) o;

        return id == gameUser.id;
    }

    @Override
    public int hashCode() {
        return id;
    }*/

}
