package com.sysu.lijun.Context;

/**
 * Created by lijun on 15/5/10.
 */
public class PlayerInfo {

    private int currentLevel;
    private int currentImageIndex;
    private PlayerInfo(){}
    private static PlayerInfo mInfo;

    static {
        mInfo = new PlayerInfo();
        mInfo.setCurrentLevel(1);
        mInfo.setCurrentImageIndex(1);
    }

    public static PlayerInfo getInfo(){
        return mInfo;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentImageIndex() {
        return currentImageIndex;
    }

    public void setCurrentImageIndex(int currentImageIndex) {
        this.currentImageIndex = currentImageIndex;
    }

    public String getCurrentImageName(){
        return currentLevel +
                "_" +
                currentImageIndex +
                ".png";
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "currentLevel=" + currentLevel +
                ", currentImageIndex=" + currentImageIndex +
                '}';
    }
}
