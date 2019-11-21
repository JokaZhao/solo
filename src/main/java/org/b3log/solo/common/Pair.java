package org.b3log.solo.common;

import org.json.JSONObject;

/**
 * Created on 2019/11/21 23:10.
 *
 * @author zhaozengjie
 * Description : 快速创建JSON
 */
public class Pair {

    private JSONObject object = new JSONObject();

    public static Pair createInstance(){
        return new Pair();
    }

    public Pair kv(String key ,String value){
        this.object.put(key,value);
        return this;
    }

    public JSONObject getJSON() {
        return object;
    }
}
