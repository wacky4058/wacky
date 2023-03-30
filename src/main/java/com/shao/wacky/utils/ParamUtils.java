package com.shao.wacky.utils;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

public class ParamUtils {

    /**
     * 组装请求参数详情
     * @param args
     * @param params
     * @return
     */
    public static String getParams(String[] args,Object[] params){
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<args.length;i++)
        {
            sb.append(args[i]+"="+params[i].toString() +";");
        }
        return sb.toString();
    }
    /**
     * 请求GET参数转Map
     *
     * @param queryString param1=xxx&param2=xxx&param3=xxx
     * @return
     */
    public static Map<String, Object> paramToMap(String queryString) {
        Map<String, Object> param = new HashMap<>(8);

        if (StrUtil.isBlank(queryString)) {
            return param;
        }

        for (String query : queryString.split("&")) {
            String[] queryParam = query.split("=");
            if (queryParam.length == 2) {
                param.put(queryParam[0], queryParam[1]);
            } else {
                param.put(queryParam[0], "");
            }
        }

        return param;
    }
}
