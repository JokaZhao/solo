package org.b3log.solo.repository;

import org.b3log.solo.AbstractTestCase;
import org.json.JSONObject;
import org.testng.annotations.Test;

/**
 * Created on 2019/11/27 22:49.
 *
 * @author zhaozengjie
 * Description :
 */
@Test(suiteName = "repository")
public class UserLoginInfoTestCase extends AbstractTestCase {

    @Test
    public void test() throws Exception{
        UserLoginInfoRepository userLoginInfoRepository = getUserLoginInfoRepository();

        JSONObject admin = userLoginInfoRepository.getByUserName("admin");

        System.out.println(admin.toString());
    }

}
