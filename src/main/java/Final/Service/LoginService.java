//package Final.Service;
//
//import ChatProgram.service.UICommonService;
//import Server.UserInfo;
//
//import java.util.Map;
//
//public class LoginService {
//    UserInfo userInfo = new UserInfo();
//    Map<String, String> loginInfo = userInfo.getLoginInfo();
//
//    Boolean checkId = loginInfo.containsKey(userId);
//
//        if (checkId) {
//        if(loginInfo.get(userId).equals(password)) {
//            return true;
//        }
//        (new UICommonService()).msg("비밀번호가 일치하지 않습니다.");
//        return false;
//    }
//        (new UICommonService()).msg("존재하지 않는 아이디입니다.");
//        return false;
//}
