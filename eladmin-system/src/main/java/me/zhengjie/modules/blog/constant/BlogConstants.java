package me.zhengjie.modules.blog.constant;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : Kahen
 * @date : 2020/12/6 23:53
 */
public class BlogConstants {
    private final static List<String> ACCESS_TOKENS = new LinkedList<>();

    static {

        ACCESS_TOKENS.add("2.00CcTx1ClhslKB466ae0c66enFS86C");
        ACCESS_TOKENS.add("2.00CcTx1CqYWeRD749febc3047IS7jC");
        ACCESS_TOKENS.add("2.00CcTx1CwanC3E512f0dcf8dggyGBB");
        ACCESS_TOKENS.add("2.00CcTx1C5gSxECb0b44b4a94ANxC8E");
    }


//    private static final String ACCESS_TOKEN = "2.00CcTx1CqYWeRD749febc3047IS7jC";
//    private static final String ACCESS_TOKEN = "2.00CcTx1CqYWeRD749febc3047IS7jC";
//private static final String ACCESS_TOKEN = "2.00CcTx1C5gSxECb0b44b4a94ANxC8E";


    public static String accessToken(Integer index) {

        return "access_token=" + ACCESS_TOKENS.get(index);
    }

    public static Integer getTokensSize() {
        return ACCESS_TOKENS.size();
    }
}
