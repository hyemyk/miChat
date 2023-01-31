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

public class LoginService {

//    // 성공시 0, 실패시 1을 반환
//    public LoginResult login(String userId, String password) {
//
//        if(userId.equals("aa")) {
//            return LoginResult.SUCCESS;
//        }
//        return LoginResult.FAIL;
//    }
    public String login(String userId, String password) {
        if(userId.equals("admin")){
            return "y";
        }
        (new UICommonService()).msg("아이디와 비밀번호를 다시 확인 하세요.");
        return "n";
    }
}
