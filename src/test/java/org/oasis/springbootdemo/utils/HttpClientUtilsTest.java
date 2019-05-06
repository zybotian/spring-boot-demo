package org.oasis.springbootdemo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianbo
 * @date 2019-04-30
 */
public class HttpClientUtilsTest {

    public static void main(String[] args) throws Exception {

        testGet();
    }

    public static void testGet() throws Exception {
        // 获取一个html文档
        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", "OEDID=oed-3bd65bbf-003b-4982-bf59-b38d6fa5b0d9");
        String response = HttpClientUtils.doGet("http://www.rayclass.com/ray/api/a/login/userinfo", headers, null);
        System.out.println(response);
    }

}
