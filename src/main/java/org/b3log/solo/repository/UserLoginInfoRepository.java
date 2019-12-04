package org.b3log.solo.repository;

import org.b3log.latke.repository.*;
import org.b3log.latke.repository.annotation.Repository;
import org.b3log.solo.model.UserLoginInfo;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2019/11/26 23:34.
 *
 * @author zhaozengjie
 * Description : 用户登录信息表
 */
@Repository
public class UserLoginInfoRepository extends AbstractRepository {

    private Logger logger = LoggerFactory.getLogger(UserLoginInfoRepository.class);

    public UserLoginInfoRepository() {
        super(UserLoginInfo.TABLE);
    }

    public JSONObject getByUserName(String userName) {
        try {
            return getFirst(new Query().setFilter(new PropertyFilter(UserLoginInfo.USER_NAME, FilterOperator.EQUAL, userName)));
        } catch (RepositoryException e) {
            logger.error("",e);
            throw new RuntimeException("查询用户登录信息错误");
        }
    }

    public JSONObject getByUserId(String userId){
        try {
            return getFirst(new Query().setFilter(new PropertyFilter(UserLoginInfo.USER_ID, FilterOperator.EQUAL, userId)));
        } catch (RepositoryException e) {
            logger.error("",e);
            throw new RuntimeException("查询用户登录信息错误");
        }
    }


    public void insert(JSONObject data){
        try {
            Transaction transaction = super.beginTransaction();
            super.add(data);
            transaction.commit();
        } catch (RepositoryException e) {
            logger.error("",e);
            throw new RuntimeException("查询用户登录信息错误");
        }
    }

}
