package com.shao.wacky.utils;

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
}
