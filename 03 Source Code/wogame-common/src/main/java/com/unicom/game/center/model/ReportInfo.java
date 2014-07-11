package com.unicom.game.center.model;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-10
 * Time: 下午2:30
 * To change this template use File | Settings | File Templates.
 */
public class ReportInfo {
    private int packageSum;
    private int sucessSum;
    private int failSum;

    public int getPackageSum() {
        return packageSum;
    }

    public void setPackageSum(int packageSum) {
        this.packageSum = packageSum;
    }

    public int getSucessSum() {
        return sucessSum;
    }

    public void setSucessSum(int sucessSum) {
        this.sucessSum = sucessSum;
    }

    public int getFailSum() {
        return failSum;
    }

    public void setFailSum(int failSum) {
        this.failSum = failSum;
    }
}
