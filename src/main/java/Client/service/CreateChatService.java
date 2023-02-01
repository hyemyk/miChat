package Client.service;

import Server.UserInfo;

import java.util.Map;

public class CreateChatService {

    public boolean userCheck(String id, String inviteeName) {

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
}
