package com.sjtu.charles.login.http.response;

/**
 * Created by charles
 * Data: 2016/5/6.
 */
public class LolLoginResponse {
    /**
     * key : 6d5fe431-2abc-45c2-932f-079e15f2bf21
     * code : 1
     */
    private String key;
    private String code;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LolLoginResponse{" +
                "key='" + key + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
