package Client.service;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class CommonUtil {
    public static ByteBuffer encode(String data) {
        Charset charset = Charset.forName("utf-8");
        return charset.encode(data);
    }
}
