package Server;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {

    public Map<String, String> getLoginInfo() {
        Map<String, String> loginInfo = new HashMap<>();

        loginInfo.put("admin", "1111");
        loginInfo.put("user1", "1111");
        loginInfo.put("user2", "1111");
        loginInfo.put("user3", "1111");
        loginInfo.put("user4", "1111");
        loginInfo.put("user5", "1111");

        return loginInfo;
    }
}

