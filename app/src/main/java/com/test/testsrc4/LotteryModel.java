package com.test.testsrc4;

/**
 * Created by Srh Dp on 30-Jun-17.
 */

public class LotteryModel {
    private int id;
    private String lotteryNo;
    private String description;

    public LotteryModel(int id, String lotteryNo, String description) {
        this.id = id;
        this.lotteryNo = lotteryNo;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getLotteryNo() {
        return lotteryNo;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLotteryNo(String LotteryNo) {
        this.lotteryNo = lotteryNo;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
