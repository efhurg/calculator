package entity;

import constant.NumType;

import java.util.Objects;
import java.util.Random;

/**
 * 真分数
 */
public class Fraction extends Type {
    /** 前面带的数 **/
    private int headNum;
    /** 分子 不能是0 要比分母小 **/
        private int numerator;
    /** 分母 不能是0 **/
    private int denominator;
    /** 真实数值 */
    private double value;

    public Fraction() {
        this.numType = NumType.FRACTION;
    }

    public String generateStr() {
        StringBuilder s = new StringBuilder();
        if(headNum > 0) {
            s.append(headNum).append("'").append(numerator).append("/").append(denominator);
            return s.toString();
        } else {
            s.append(numerator).append("/").append(denominator);
            return s.toString();
        }
    }

    public int getHeadNum() {
        return headNum;
    }

    public void setHeadNum(int headNum) {
        this.headNum = headNum;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /** 获取随机值 */
    @Override
    public void getRandom(int range) {
        // 随机对象
        Random random = new Random();
        // 在指定范围内分别生成分数的各个部分的数字
        this.headNum = random.nextInt(range) + 1;
        // 分子不能为n
        this.numerator = random.nextInt(range-1) + 1;
        // 分母不为0 要比分子大
        int temp = random.nextInt(range) + 1;
        while(temp <= 0 || temp <= this.numerator) {
            temp = random.nextInt(range) + 1;
        }
        this.denominator = temp;
        this.value = this.headNum + (double)this.numerator / this.denominator;
        this.realVal = this.value;
    }

    /** 判断相等 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return headNum == fraction.headNum &&
                numerator == fraction.numerator &&
                denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(headNum, numerator, denominator);
    }
}
