package entity;

import constant.NumType;

public abstract class Type {
    /** 数字类型标识 */
    protected NumType numType;
    /** 真实数值 */
    protected double realVal;

    public Type() {
    }

    public abstract void getRandom(int range);

    public NumType getNumType() {
        return numType;
    }

    public void setNumType(NumType numType) {
        this.numType = numType;
    }

    public double getRealVal() {
        return realVal;
    }

    public void setRealVal(double realVal) {
        this.realVal = realVal;
    }
}
