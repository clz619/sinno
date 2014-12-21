/**
 * 
 */
package sinno.test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @project sinno
 * @title URITest.java
 * @author lizhong.chen
 * @data 2014年11月29日下午11:56:03
 * @description TODO
 * @tag
 * @version V1.0
 */
public class URITest {

    public static void main(String[] args) throws URISyntaxException {
        URI redisUri = new URI("redis://username:password@localhost:100");

        System.out.println(redisUri.getHost());

        String userInfo = redisUri.getUserInfo();
        if (redisUri.getPort() > 0) {
            System.out.println(redisUri.getPort());
        }

        if (userInfo != null) {
            String[] parsedUserInfo = userInfo.split(":");
            System.out.println(parsedUserInfo[(parsedUserInfo.length - 1)]);
        }

    }
}
