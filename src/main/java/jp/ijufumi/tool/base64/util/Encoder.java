package jp.ijufumi.tool.base64.util;

import org.apache.commons.codec.binary.Base64;

public class Encoder {

    public static String doEncode(String value) {
        return new String(Base64.encodeBase64(value.getBytes()));
    }

    public static String doEncodeChunked(String value) {
        return new String(Base64.encodeBase64(value.getBytes(), true));
    }

    public static String doDecode(String value) {
        return new String(Base64.decodeBase64(value));
    }
}
