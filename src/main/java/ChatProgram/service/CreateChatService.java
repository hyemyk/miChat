package ChatProgram.service;

import ChatProgram.ChatRoomPkg.ChatRoom;
import ChatProgram.ChatRoomPkg.User;
import Server.UserInfo;

import java.util.ArrayList;
import java.util.Map;

import static ChatProgram.ChatRoomPkg.RoomManager.roomList;

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

    public void createRoom(User loginUserInfo, String roomName, ArrayList<String> inviteeList) { // 룸을 새로 생성
        //ChatRoom 인스턴스 생성
        ChatRoom createdRoom = new ChatRoom();
        createdRoom.setRoomName(roomName);
        createdRoom.setInviteeList(inviteeList);

        //생성된 방을 RoomManager의 roomList에 추가
        roomList.add(createdRoom);

        //??????로그인 유저 정보에 생성된 모든 룸 정보 저장???????????
        loginUserInfo.setRoomList(roomList);

        System.out.println("Room Created!");
    }
}
