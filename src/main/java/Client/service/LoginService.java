package Client.service;

//public enum LoginResult {
//    SUCCESS(0), FAIL(1);
//
//    private final int value;
//    LoginResult(int value) {
//        this.value = value;
//    }
//    public int getValue() {
//        return value;
//    }
//}

import Server.UserInfo;

import java.util.Map;

public class LoginService {

//    // 성공시 0, 실패시 1을 반환
//    public LoginResult login(String userId, String password) {
//
//        if(userId.equals("aa")) {
//            return LoginResult.SUCCESS;
//        }
//        return LoginResult.FAIL;
//    }
    public Boolean login(String userId, String password) {
        UserInfo userInfo = new UserInfo();
        Map<String, String> loginInfo = userInfo.getLoginInfo();

        Boolean checkId = loginInfo.containsKey(userId);

        if (checkId) {
            if(loginInfo.get(userId).equals(password)) {
                return true;
            }
            (new UICommonService()).msg("비밀번호가 일치하지 않습니다.");
            return false;
        }
        (new UICommonService()).msg("존재하지 않는 아이디입니다.");
        return false;

    }
}
