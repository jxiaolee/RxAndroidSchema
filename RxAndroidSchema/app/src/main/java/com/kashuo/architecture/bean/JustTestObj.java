package com.kashuo.architecture.bean;

/**
 * Author:  LiXiao
 * Email:   lixiao@kashuo.com
 * Date:    2017/4/24
 * Description:
 */

public class JustTestObj {

    private int justNumber;
    private String justName;
    private User justUser;
    private boolean justTag;

    public int getJustNumber() {
        return justNumber;
    }

    public void setJustNumber(int justNumber) {
        this.justNumber = justNumber;
    }

    public String getJustName() {
        return justName;
    }

    public void setJustName(String justName) {
        this.justName = justName;
    }

    public User getJustUser() {
        return justUser;
    }

    public void setJustUser(User justUser) {
        this.justUser = justUser;
    }

    public boolean isJustTag() {
        return justTag;
    }

    public void setJustTag(boolean justTag) {
        this.justTag = justTag;
    }

    @Override
    public String toString() {
        return "JustTestObj{" +
                "justNumber=" + justNumber +
                ", justName='" + justName + '\'' +
                ", justUser=" + justUser +
                ", justTag=" + justTag +
                '}';
    }
}
