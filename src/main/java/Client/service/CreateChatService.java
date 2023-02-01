package Client.service;

import Client.DTO.ChatRoomDTO;
import Client.DTO.UserDTO;
import Server.UserInfo;

import java.util.ArrayList;
import java.util.Map;

import static Client.DTO.RoomManager.roomList;

public class CreateChatService {

    public boolean userCheck(String id, String inviteeName) {
        System.out.println("id: " + id);
        System.out.println("inviteeName: " + inviteeName);
        if(inviteeName == null) {
            (new UICommonService()).msg("초대할 사람을 입력해 주세요.");
            return false;
        }

        if (id.equals(inviteeName)) {
            (new UICommonService()).msg("자기 자신을 초대할 수 없습니다.");
            return false;
        }

        Map<String, String> users = (new UserInfo()).getLoginInfo();
        Boolean check = users.containsKey(inviteeName);
        if (!check) {
            (new UICommonService()).msg("존재하지 않는 유저입니다.");
            return false;
        }

        return true;
    }
    public boolean createCheck(String roomName, String listBox) {

        if (roomName == null || listBox == null) {
            (new UICommonService()).msg("채팅방 이름과 초대할 사람을 입력해주세요.");
            return false;
        }

        //이미 들어가있는 방인지 확인
//        if (roomName.equals()){
//        }

        return true;
    }

    public void createRoom(UserDTO loginUserInfo, String roomName, ArrayList<String> inviteeList) { // 룸을 새로 생성
        ChatRoomDTO room = new ChatRoomDTO(roomName);
        roomList.add(room);
        loginUserInfo.setRoomList(roomList);
        System.out.println("Room Created!");
    }
}
