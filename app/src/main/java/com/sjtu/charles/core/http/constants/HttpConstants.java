package com.sjtu.charles.core.http.constants;

/**
 * Created by charles
 * Data: 2016/3/30.
 */
public class HttpConstants {
    /**
     * lol主机查询服务接口
     */
//    public static final String LOL_HOST = "http://www.games-cube.com/combat/api/";

    public static final String LOL_ACCOUNT = "zhaohuanmao";

    public static final String LOL_PASSWORD = "1qaz@WSX";

    public enum Env {
        PROD("www.games-cube.com/combat"), //lol主机查询服务接口
        DEV("www.games-cube.com/test"); //TODO 测试链接，待定

        public final String host;
        Env(String host) {
            this.host = host;
        }
    }

    // API 要使用的 API 版本
    public final static int VERSION = 1;

    //TODO 设置网络环境
    public final static Env env = Env.PROD;

    /**
     * 区分版本号
     * @param env
     * @param version
     * @return
     */
    public static String getEndPoint(Env env, int version) {
        return String.format("http://%s/api/v%d", env.host, version);
    }

    public static String getEndPoint(Env env) {
        return String.format("http://%s/api/", env.host);
    }
}
