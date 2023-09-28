package entity;

import constant.NumType;

import java.util.Objects;
import java.util.Random;

public class Ordinary extends Type{
    /** 数值 */
    private int value;

    public Ordinary() {
        this.numType = NumType.ORDINARY;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /** 获取随机值 */
    @Override
    public void getRandom(int range) {
        this.value = new Random().nextInt(range) + 1;
        this.realVal = this.value;
    }

    /** 判断相等 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordinary ordinary = (Ordinary) o;
        return value == ordinary.value;
    }



    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
