package ChatProgram.ChatRoomPkg;

import java.net.Socket;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;

public class User {
    private String id;	// 아이디


    private String pw; // 비밀번호
    private ArrayList<ChatRoom> roomList;  // 유저가 속한 룸 List
    private AsynchronousSocketChannel socket; // 소켓 object

    public User() { // 아무런 정보가 없는 깡통 유저를 만들 때
    }

    public User(String id) { // 아이디 정보만 가지고 생성
        this.id = id;
    }

//    /**
//     * 방에 입장시킴
//     * room  입장할 방
//     */
//    public void enterRoom(ChatRoom room) {
//        room.enterUser(this); // 룸에 입장시킨 후
//        this.room = room; // 유저가 속한 방을 룸으로 변경한다.(중요)
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public ArrayList<ChatRoom> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<ChatRoom> roomList) {
        this.roomList = roomList;
    }

    public AsynchronousSocketChannel getSocket() {
        return socket;
    }

    public void setSocket(AsynchronousSocketChannel socket) {
        this.socket = socket;
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
