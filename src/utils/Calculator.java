package utils;

import entity.Fraction;
import entity.Ordinary;
import entity.Type;

/**
 * 计算类
 */
public class Calculator {
    /**
     * 求a和b的最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int f(int a,int b){
        if(a < b){
            int c = a;
            a = b;
            b = c;
        }
        int r = a % b;
        while(r != 0){
            a = b;
            b = r;;
            r = a % b;
        }
        return b;
    }

    /** 求两个分数运算后的分子 **/
    public static int CalculateNumer(Fraction fra1, Fraction fra2, int op){
        if (op == 1){
            if (fra1.getDenominator() == fra2.getDenominator()){
                return fra1.getNumerator() + fra2.getNumerator();
            }
            return fra1.getNumerator() * fra2.getDenominator() + fra2.getNumerator() * fra1.getDenominator();
        }
        if (op == 2){
            if (fra1.getDenominator() == fra2.getDenominator()){
                return fra1.getNumerator() - fra2.getNumerator();
            }
            return fra1.getNumerator() * fra2.getDenominator() - fra2.getNumerator() * fra1.getDenominator();
        }
        return 0;
    }

    /** 求两个分数运算后的分母 **/
    public static int CalculateDenomin(Fraction fra1, Fraction fra2){
        if (fra1.getDenominator() == fra2.getDenominator()){
            return fra1.getDenominator();
        }
        return fra1.getDenominator() * fra2.getDenominator();
    }

    /** 求带分数化假分数的分子 **/
    public static int CalculateNumer(Fraction fra){
        return fra.getHeadNum() * fra.getDenominator() + fra.getNumerator();
    }

    /** 两个数都是 Ordinary 类型 **/
    public static Type calculate(Ordinary ord1, Ordinary ord2, int op){
        if (op == 1){
            Ordinary ordinary = new Ordinary();
            ordinary.setValue(ord1.getValue() + ord2.getValue());
            return ordinary;
        }
        if (op == 2){
            Ordinary ordinary = new Ordinary();
            ordinary.setValue(ord1.getValue() - ord2.getValue());
            return ordinary;
        }
        if (op == 3){
            Ordinary ordinary = new Ordinary();
            ordinary.setValue(ord1.getValue() * ord2.getValue());
            return ordinary;
        }
        if (op == 4){
            // 两个普通数相除为分数则返回 Fraction 类型
            if ((ord1.getValue() % ord2.getValue()) != 0){
                Fraction fraction = new Fraction();
                fraction.setHeadNum(ord1.getValue() / ord2.getValue());
                fraction.setNumerator(ord1.getValue() % ord2.getValue());
                fraction.setDenominator(ord2.getValue());
                return getType(fraction);
            } else{
                Ordinary ordinary = new Ordinary();
                ordinary.setValue(ord1.getValue() / ord2.getValue());
                return ordinary;
            }
        }
        return null;
    }

    /** 第一个数是 Fraction 类型，第二个数是 Ordinary 类型 **/
    public static Type calculate(Fraction fra1, Ordinary ord2, int op){
        if (op == 1){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(fra1.getHeadNum() + ord2.getValue());
            fraction.setNumerator(fra1.getNumerator());
            fraction.setDenominator(fra1.getDenominator());
            return getType(fraction);
        }
        if (op == 2){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(fra1.getHeadNum() - ord2.getValue());
            if (fraction.getHeadNum() < 0){
                fraction.setNumerator( - fra1.getNumerator());
            }else{
                fraction.setNumerator(fra1.getNumerator());
            }
            fraction.setDenominator(fra1.getDenominator());
            return getType(fraction);
        }
        if (op == 3){
            Fraction fraction = new Fraction();
            // 新 HeadNum 等于旧 HeadNum 乘整数加上旧分子除以分母
            fraction.setHeadNum((fra1.getHeadNum() * fra1.getDenominator() + fra1.getNumerator()) * ord2.getValue() / fra1.getDenominator());
            // 新分子等于整数与旧分母取模
            fraction.setNumerator((fra1.getHeadNum() * fra1.getDenominator() + fra1.getNumerator()) * ord2.getValue() % fra1.getDenominator());
            fraction.setDenominator(fra1.getDenominator());

            return getType(fraction);
        }
        if (op == 4){
            Fraction fraction = new Fraction();
            fraction.setHeadNum((fra1.getHeadNum() * fra1.getDenominator() + fra1.getNumerator()) / (fra1.getDenominator() * ord2.getValue()));
            fraction.setNumerator((fra1.getHeadNum() * fra1.getDenominator() + fra1.getNumerator()) % (fra1.getDenominator() * ord2.getValue()));
            fraction.setDenominator(fra1.getDenominator() * ord2.getValue());

            return getType(fraction);
        }
        return null;
    }

    /** 第一个数是 Ordinary 类型，第二个数是 Fraction 类型 **/
    public static Type calculate(Ordinary ord1, Fraction fra2, int op){
        if (op == 1){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(fra2.getHeadNum() + ord1.getValue());
            fraction.setNumerator(fra2.getNumerator());
            fraction.setDenominator(fra2.getDenominator());

            return getType(fraction);
        }
        if (op == 2){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(ord1.getValue() - fra2.getHeadNum() - 1);
            fraction.setNumerator(fra2.getDenominator() - fra2.getNumerator());
            fraction.setDenominator(fra2.getDenominator());

            return getType(fraction);
        }
        if (op == 3){
            Fraction fraction = new Fraction();
            fraction.setHeadNum((fra2.getHeadNum() * fra2.getDenominator() + fra2.getNumerator()) * ord1.getValue() / fra2.getDenominator());
            fraction.setNumerator((fra2.getHeadNum() * fra2.getDenominator() + fra2.getNumerator()) * ord1.getValue() % fra2.getDenominator());
            fraction.setDenominator(fra2.getDenominator());

            return getType(fraction);
        }
        if (op == 4){
            int commonDiviser;
            Fraction fraction = new Fraction();
            // 新 headNum 等于整数除以旧 HeadNum 乘分母加分子
            fraction.setHeadNum((ord1.getValue() * fra2.getDenominator()) / (fra2.getHeadNum() * fra2.getDenominator() + fra2.getNumerator()));
            // 新分子等于整数乘分母与旧 HeadNum 乘分母加分子取模
            fraction.setNumerator((ord1.getValue() * fra2.getDenominator()) % (fra2.getHeadNum() * fra2.getDenominator() + fra2.getNumerator()));
            // 新分母等于旧 HeadNum 乘分母加分子
            fraction.setDenominator(fra2.getHeadNum() * fra2.getDenominator() + fra2.getNumerator());

            return getType(fraction);
        }
        return null;
    }

    /** 两个数都是 Fraction 类型 **/
    public static Type calculate(Fraction fra1, Fraction fra2, int op){
        if (op == 1){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(fra1.getHeadNum() + fra2.getHeadNum());
            fraction.setNumerator(CalculateNumer(fra1, fra2, 1));
            fraction.setDenominator(CalculateDenomin(fra1, fra2));
            // 分数相加大于1时进1
            if (fraction.getNumerator() > fraction.getDenominator()){
                fraction.setHeadNum(fraction.getHeadNum() + 1);
                fraction.setNumerator(fraction.getNumerator() - fraction.getDenominator());
            }

            return getType(fraction);
        }
        if (op == 2){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(fra1.getHeadNum() - fra2.getHeadNum());
            fraction.setNumerator(CalculateNumer(fra1, fra2, 2));
            fraction.setDenominator(CalculateDenomin(fra1, fra2));

            // 此处若整数部分相减为负数时存在bug，但不考虑这种情况发生
            // 如果分子为负数则向 headNum 借1进行计算
            if (fraction.getHeadNum() + fraction.getNumerator()== 0){
                Ordinary ordinary = new Ordinary();
                ordinary.setValue(fraction.getHeadNum());
                return ordinary;
            } else if (fraction.getNumerator() < 0){
                fraction.setHeadNum(fraction.getHeadNum() - 1);
                fraction.setNumerator(fraction.getDenominator() + fraction.getNumerator());
            }
            return getType(fraction);
        }
        if (op == 3){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(CalculateNumer(fra1) * CalculateNumer(fra2) / (fra1.getDenominator() * fra2.getDenominator()));
            fraction.setNumerator(CalculateNumer(fra1) * CalculateNumer(fra2) % (fra1.getDenominator() * fra2.getDenominator()));
            fraction.setNumerator(fra1.getDenominator() * fra2.getDenominator());

            return getType(fraction);
        }
        if (op == 4){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(CalculateNumer(fra1) * fra2.getDenominator() / (fra1.getDenominator() * CalculateNumer(fra2)));
            fraction.setNumerator(CalculateNumer(fra1) * fra2.getDenominator() % (fra1.getDenominator() * CalculateNumer(fra2)));
            fraction.setDenominator(fra1.getDenominator() * CalculateNumer(fra2));

            return getType(fraction);
        }
        return null;
    }

    /** 对带分数进行约分 **/
    private static Type getType(Fraction fraction) {
        // 当分子是 0 时，转化为 ordinary 类型
        if (fraction.getNumerator() == 0){
            Ordinary ordinary = new Ordinary();
            ordinary.setValue(fraction.getHeadNum());
            return ordinary;
        }
        // 获取分子分母的最大公因数并约分
        int commonDiviser;
        commonDiviser = f(fraction.getNumerator(), fraction.getDenominator());
        if (commonDiviser != 1){
            fraction.setNumerator(fraction.getNumerator() / commonDiviser);
            fraction.setDenominator(fraction.getDenominator() / commonDiviser);
            // 可化为整数则将其转化为 Ordinary 类型返回
            if (fraction.getDenominator() == fraction.getNumerator()){
                Ordinary ordinary = new Ordinary();
                ordinary.setValue(fraction.getHeadNum());
                return ordinary;
            }
            return fraction;
        }
        return fraction;
    }
}