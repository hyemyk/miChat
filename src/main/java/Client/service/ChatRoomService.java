package Client.service;

import Client.DTO.ChatRoomDTO;
import Client.DTO.UserDTO;

import static Client.DTO.RoomManager.roomList;

public class ChatRoomService {

    /*
inviteeList 나를 지우고, roomList(내가 속한 방들)에서 룸 지우기
 */
    public void exitRoom(ChatRoomDTO room) {

        roomList.remove(room);
    }


    /**
     * 해당 룸의 유저를 다 퇴장시키고 삭제함
     */
    public void close() { /**/
        for (UserDTO user : inviteeList) {
            user.exitRoom(this);
        }
        this.inviteeList.clear();
        this.inviteeList = null;
    }
}
