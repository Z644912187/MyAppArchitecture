package com.sjtu.charles.playmate.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by charles on 2016/5/23.
 */
public class PlaymateInfo implements Serializable {
    private static final long serialVersionUID = -2431302683946145512L;
    private String gender;
    private Date startTime;
    private Date endTime;
    private String playPrice;
    private String gameName;
    private boolean needVideo;
    private boolean needVoice;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPlayPrice() {
        return playPrice;
    }

    public void setPlayPrice(String playPrice) {
        this.playPrice = playPrice;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean isNeedVideo() {
        return needVideo;
    }

    public void setNeedVideo(boolean needVideo) {
        this.needVideo = needVideo;
    }

    public boolean isNeedVoice() {
        return needVoice;
    }

    public void setNeedVoice(boolean needVoice) {
        this.needVoice = needVoice;
    }
}
