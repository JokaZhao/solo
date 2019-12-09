package org.b3log.solo;

import org.b3log.solo.encrypt.PasswordHash;
import org.b3log.solo.repository.UserLoginInfoRepository;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

/**
 * Created on 2019/12/4 23:09.
 *
 * @author zhaozengjie
 * Description : 用来在表中生成一个admin用户
 */
public class InitUser extends AbstractTestCase {

    @Test
    public void initUser() throws InvalidKeySpecException, NoSuchAlgorithmException {

        UserLoginInfoRepository userLoginInfoRepository = getUserLoginInfoRepository();

        String userName = "Joka";

        String password = "dominjoka0502";

        JSONObject user = new JSONObject();
        user.put("user_name", userName);
        user.put("id",10000);
        user.put("user_type", "admin");
        user.put("user_id", UUID.randomUUID().toString().replace("-",""));

        String salt = UUID.randomUUID().toString().replace("-","").substring(0,24);

        System.out.println("salt is :" + salt);

        String pending = salt + password;
        String hash = PasswordHash.createHash(pending);
        user.put("password", hash);
        user.put("salt",salt);

        userLoginInfoRepository.insert(user);


    }

}
