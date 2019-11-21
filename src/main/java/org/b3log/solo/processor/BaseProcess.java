package org.b3log.solo.processor;

import org.json.JSONObject;

/**
 * Created on 2019/11/21 18:16.
 *
 * @author zhaozengjie
 * Description :
 */
public abstract class BaseProcess {

    protected JSONObject err(String msg){

        JSONObject err = new JSONObject();
        err.put("resultCode","000001");
        err.put("resultMsg",msg);
        return err;

    }

}
