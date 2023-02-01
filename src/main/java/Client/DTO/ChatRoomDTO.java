package Client.DTO;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomDTO {
    private List<UserDTO> inviteeList; // 방 참가자 리스트
    private String roomName; // 방 이름


    public ChatRoomDTO(String roomName) { // 유저가 방을 만들때 생성자
        this.roomName = roomName;
    }


    public ChatRoomDTO(List<UserDTO> users) { // 유저 리스트가 방을 생성할 /**/
        this.inviteeList = users; // 유저리스트 복사

        // 룸 입장
        for(UserDTO user : users){
            user.enterRoom(this);
        }

    }

//    public void enterUser(UserDTO user) {
//        user.enterRoom(this);
//        inviteeList.add(user);
//    }
//
//    public void enterUser(List<UserDTO> users) { /**/
//        for(UserDTO user : users){
//            user.enterRoom(this);
//        }
//        inviteeList.addAll(users);
//    }

//


    // 게임 로직

    /**
     * 해당 byte 배열을 방의 모든 유저에게 전송
     * @param data 보낼 data
     */
    public void broadcast(byte[] data) {
        for (UserDTO user : inviteeList) { // 방에 속한 유저의 수만큼 반복
            // 각 유저에게 데이터를 전송하는 메서드 호출~
            // ex) user.SendData(data);

//			try {
//				user.sock.getOutputStream().write(data); // 이런식으로 바이트배열을 보낸다.
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        }
    }





    public String getRoomName() { // 방 이름을 가져옴
        return roomName;
    }

    public int getUserSize() { // 유저의 수를 리턴
        return inviteeList.size();
    }


    public List getInviteeList() {
        return inviteeList;
    }

    public void setInviteeList(List inviteeList) {
        this.inviteeList = inviteeList;
    }

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatRoomDTO chatRoom = (ChatRoomDTO) o;

        return id == chatRoom.id;
    }

    @Override
    public int hashCode() {
        return id;
    }*/
}
