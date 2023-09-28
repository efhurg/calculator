package entity;

import constant.NumType;
import constant.OP;
import utils.Calculator;

import java.util.*;

public class Question {
    /** 数字数量 */
    int numsCount;
    /** 算式类型 */
    int equation;
    /** 数字集合 */
    private List<Type> nums;
    /** 操作符队列 */
    private Queue<OP> nops = new LinkedList<>();
    /** 题目字符串 */
    private StringBuilder qStr = new StringBuilder();
    /** 答案 初始化默认为普通整数否则会报空指针错误*/
    private Type ans = new Ordinary();
    /** 随机对象 */
    private Random random = new Random();
    /** 数值限定范围 */
    private int range;


    /**
     * 生成题目
     */
    public void generateQuestion() {
        // 数字数量
        numsCount = random.nextInt(3) + 2;

        switch (numsCount) {
            case 2 : {
                equation = 1;
                // 随机生成两个数
                Type a = getRandomTypeNum(), b = getRandomTypeNum();
                // 随机生成操作符
                getRandomOP();
                OP op = nops.poll();
                if(isNeedSwap(a, b, op)) {
                    Type temp = a;
                    a = b;
                    b = temp;
                }
                // 生成算式字符串
                generateEquationStr(a, b, op, false);
                // 计算答案
                ans = twoNumEquationCal(a, b, op);
                qStr.append(" ").append("=");
                break;
            }
            case 3: {
                // 算式2~4号
                equation = random.nextInt(3)+2;
                // 随机生成三个数
                Type a = getRandomTypeNum(), b = getRandomTypeNum(), c = getRandomTypeNum();
                // 随机生成操作符
                getRandomOP();
                OP firstOP = nops.poll();
                OP secondOP = nops.poll();

                if(equation == 2 || equation == 3) { // (1 + 2) + 3 或 1 + (2 + 3)
                    if(isNeedSwap(a, b, firstOP)) {
                        Type temp = a;
                        a = b;
                        b = temp;
                    }
                    // 生成算式字符串
                    generateEquationStr(a, b, firstOP, true);
                    Type res = twoNumEquationCal(a, b, firstOP);
                    boolean isSwap = isNeedSwap(res, c, secondOP);
                    if(c.getNumType().getCode() == NumType.FRACTION.getCode()) {
                        Fraction cc = (Fraction) c;
                        if(isSwap) {
                            qStr.insert(0, " ").insert(0, secondOP.getSign()).insert(0, " ");
                            qStr.insert(0, cc.generateStr());
                            ans = twoNumEquationCal(cc, res, secondOP);
                        } else {
                            qStr.append(" ").append(secondOP.getSign()).append(" ");
                            qStr.append(cc.generateStr());
                            ans = twoNumEquationCal(res, cc, secondOP);
                        }
                    } else {
                        Ordinary cc = (Ordinary) c;
                        if(isSwap) {
                            qStr.insert(0, " ").insert(0, secondOP.getSign()).insert(0, " ");
                            qStr.insert(0, cc.getValue());
                            ans = twoNumEquationCal(cc, res, secondOP);
                        } else {
                            qStr.append(" ").append(secondOP.getSign()).append(" ");
                            qStr.append(cc.getValue());
                            ans = twoNumEquationCal(res, cc, secondOP);
                        }
                    }
                } else if(equation == 4) { // 1 + 2 + 3
                    // 优先级判断，(加为1, 减为2 - 1) / 2 后为0， 乘除号运算后为1
                    if((firstOP.getCode() - 1) / 2 >= (secondOP.getCode() - 1) / 2) {
                        if(isNeedSwap(a, b, firstOP)) {
                            Type temp = a;
                            a = b;
                            b = temp;
                        }
                        // 生成算式字符串
                        generateEquationStr(a, b, firstOP, false);
                        Type res = twoNumEquationCal(a, b, firstOP);
                        boolean isSwap = isNeedSwap(res, c, secondOP);
                        if(c.getNumType().getCode() == NumType.FRACTION.getCode()) {
                            Fraction cc = (Fraction) c;
                            if(isSwap) {
                                qStr.insert(0, " ").insert(0, secondOP.getSign()).insert(0, " ");
                                qStr.insert(0, cc.generateStr());
                                ans = twoNumEquationCal(cc, res, secondOP);
                            } else {
                                qStr.append(" ").append(secondOP.getSign()).append(" ");
                                qStr.append(cc.generateStr());
                                ans = twoNumEquationCal(res, cc, secondOP);
                            }
                        } else {
                            Ordinary cc = (Ordinary) c;
                            if(isSwap) {
                                qStr.insert(0, " ").insert(0, secondOP.getSign()).insert(0, " ");
                                qStr.insert(0, cc.getValue());
                                ans = twoNumEquationCal(cc, res, secondOP);
                            } else {
                                qStr.append(" ").append(secondOP.getSign()).append(" ");
                                qStr.append(cc.getValue());
                                ans = twoNumEquationCal(res, cc, secondOP);
                            }
                        }
                    } else {
                        if(isNeedSwap(b, c, secondOP)) {
                            Type temp = b;
                            b = c;
                            c = temp;
                        }
                        // 生成算式字符串
                        generateEquationStr(b, c, secondOP, false);
                        Type res = twoNumEquationCal(b, c, secondOP);
                        boolean isSwap = isNeedSwap(a, res, firstOP);
                        if(a.getNumType().getCode() == NumType.FRACTION.getCode()) {
                            Fraction aa = (Fraction) a;
                            if(isSwap) {
                                qStr.append(" ").append(firstOP.getSign()).append(" ");
                                qStr.append(aa.generateStr());
                                ans = twoNumEquationCal(res, a, firstOP);
                            } else {
                                qStr.insert(0, " ").insert(0, firstOP.getSign()).insert(0, " ");
                                qStr.insert(0, aa.generateStr());
                                ans = twoNumEquationCal(a, res, firstOP);
                            }
                        } else {
                            Ordinary aa = (Ordinary) a;
                            if(isSwap) {
                                qStr.append(" ").append(firstOP.getSign()).append(" ");
                                qStr.append(aa.getValue());
                                ans = twoNumEquationCal(res, a, firstOP);
                            } else {
                                qStr.insert(0, " ").insert(0, firstOP.getSign()).insert(0, " ");
                                qStr.insert(0, aa.getValue());
                                ans = twoNumEquationCal(a, res, firstOP);
                            }
                        }
                    }
                }
                qStr.append(" ").append("=");
                break;
            }
            case 4: {
                // 算式5~7号
                equation = random.nextInt(3)+5;
                // 随机生成四个数
                Type a = getRandomTypeNum(), b = getRandomTypeNum(), c = getRandomTypeNum(), d = getRandomTypeNum();
                // 随机生成操作符
                getRandomOP();
                OP firstOP = nops.poll();
                OP secondOP = nops.poll();
                OP thirdOP = nops.poll();
                if(equation == 5 || equation == 6 || equation == 7) {
                    if(isNeedSwap(a, b, firstOP)) {
                        Type temp = a;
                        a = b;
                        b = temp;
                    };
                    // 生成算式字符串
                    generateEquationStr(a, b, firstOP, true);
                    Type res = twoNumEquationCal(a, b, firstOP);
                    if((secondOP.getCode() - 1) / 2 >= (thirdOP.getCode() - 1) / 2) {
                        Boolean isSwap1 = isNeedSwap(res, c, secondOP);
                        if(c.getNumType().getCode() == NumType.FRACTION.getCode()) {
                            Fraction cc = (Fraction) c;
                            if(isSwap1) {
                                qStr.insert(0, " ").insert(0, secondOP.getSign()).insert(0, " ");
                                qStr.insert(0, cc.generateStr());
                                res = twoNumEquationCal(cc, res, secondOP);
                            } else {
                                qStr.append(" ").append(secondOP.getSign()).append(" ");
                                qStr.append(cc.generateStr());
                                res = twoNumEquationCal(res, cc, secondOP);
                            }
                        } else {
                            Ordinary cc = (Ordinary) c;
                            if(isSwap1) {
                                qStr.insert(0, " ").insert(0, secondOP.getSign()).insert(0, " ");
                                qStr.insert(0, cc.getValue());
                                res = twoNumEquationCal(cc, res, secondOP);
                            } else {
                                qStr.append(" ").append(secondOP.getSign()).append(" ");
                                qStr.append(cc.getValue());
                                res = twoNumEquationCal(res, cc, secondOP);
                            }
                        }
                        Boolean isSwap2 = isNeedSwap(res, d, thirdOP);
                        if(d.getNumType().getCode() == NumType.FRACTION.getCode()) {
                            Fraction dd = (Fraction) d;
                            if(isSwap2) {
                                qStr.insert(0, " ").insert(0, thirdOP.getSign()).insert(0, " ");
                                qStr.insert(0, dd.generateStr());
                                ans = twoNumEquationCal(dd, res, thirdOP);
                            } else {
                                qStr.append(" ").append(thirdOP.getSign()).append(" ");
                                qStr.append(dd.generateStr());
                                ans = twoNumEquationCal(res, dd, thirdOP);
                            }
                        } else {
                            Ordinary dd = (Ordinary) d;
                            if(isSwap2) {
                                qStr.insert(0," ").insert(0, thirdOP.getSign()).insert(0, " ");
                                qStr.insert(0, dd.getValue());
                                ans = twoNumEquationCal(dd, res, thirdOP);
                            } else {
                                qStr.append(" ").append(thirdOP.getSign()).append(" ");
                                qStr.append(dd.getValue());
                                ans = twoNumEquationCal(res, dd, thirdOP);
                            }
                        }
                    } else {
                        if(isNeedSwap(c, d, thirdOP)) {
                            Type temp = c;
                            c = d;
                            d = temp;
                        };
                        Type res1 = twoNumEquationCal(c, d, thirdOP);
                        Boolean needSwap = isNeedSwap(res, res1, secondOP);
                        if(needSwap) {
                            String temp = qStr.toString();
                            qStr = new StringBuilder();
                            generateEquationStr(c, d, thirdOP, false);
                            qStr.append(" ").append(secondOP.getSign()).append(" ");
                            qStr.append(temp);
                            ans = twoNumEquationCal(res1, res, secondOP);
                        } else {
                            qStr.append(" ").append(secondOP.getSign()).append(" ");
                            // 生成算式字符串
                            generateEquationStr(c, d, thirdOP, false);
                            ans = twoNumEquationCal(res, res1, secondOP);
                        }
                    }
                }
                qStr.append(" ").append("=");
                break;
            }
        }
    }

    /**
     * 两数算式计算
     * @param a
     * @param b
     * @param op
     * @return
     */
    public Type twoNumEquationCal(Type a, Type b, OP op) {
        // 计算答案
        return cal(a, b, op.getCode());
    }

    /**
     * 操作符是否为减号且a < b
     * @param a
     * @param b
     * @param op
     * @return
     */
    public Boolean isNeedSwap(Type a, Type b, OP op) {

        if(op.getCode() == OP.SUB.getCode() && a.getRealVal() < b.getRealVal()) {
            return true;
        }
        return false;
    }

    /**
     * 计算
     * @param a
     * @param b
     * @return
     */
    public Type cal(Type a, Type b, int code) {
        Type result = null;
        int aType = a.getNumType().getCode(), bType = b.getNumType().getCode();
        if(aType == NumType.ORDINARY.getCode() && bType == NumType.ORDINARY.getCode()) {
            Ordinary aa = (Ordinary)a, bb = (Ordinary)b;
            result = Calculator.calculate(aa, bb, code);
        } else if(aType == NumType.FRACTION.getCode() && bType == NumType.FRACTION.getCode()) {
            Fraction aa = (Fraction)a, bb = (Fraction)b;
            result = Calculator.calculate(aa, bb, code);
        } else {
            if(aType == NumType.FRACTION.getCode()) {
                Fraction aa = (Fraction)a;
                Ordinary bb = (Ordinary)b;
                result = Calculator.calculate(aa, bb, code);
            } else {
                Ordinary aa = (Ordinary)a;
                Fraction bb = (Fraction)b;
                result = Calculator.calculate(aa, bb, code);
            }
        }
        // 返回前计算realVal并设置
        if(result.getNumType().getCode() == NumType.FRACTION.getCode()) {
            Fraction rRes = (Fraction) result;
            result.setRealVal(rRes.getHeadNum() + (double)rRes.getNumerator() / rRes.getDenominator());
        } else {
            Ordinary oRes = (Ordinary) result;
            result.setRealVal(oRes.getValue());
        }
        return result;
    }

    /**
     * 生成2数字算式字符串
     * @param a
     * @param b
     * @param op
     * @param hasBreaket
     */
    public void generateEquationStr(Type a, Type b, OP op, Boolean hasBreaket) {
        if(hasBreaket) {
            qStr.append("(");
        }
        int aType = a.numType.getCode(), bType = b.getNumType().getCode();
        if(aType == NumType.ORDINARY.getCode() && bType == NumType.ORDINARY.getCode()) {
            Ordinary aa = (Ordinary)a, bb = (Ordinary)b;
            qStr.append(aa.getValue()).append(" ").append(op.getSign()).append(" ").append(bb.getValue());
        } else if(aType == NumType.FRACTION.getCode() && bType == NumType.FRACTION.getCode()) {
            Fraction aa = (Fraction)a, bb = (Fraction)b;
            qStr.append(aa.generateStr()).append(" ").append(op.getSign()).append(" ").append(bb.generateStr());
        } else {
            if(aType == NumType.FRACTION.getCode()) {
                Fraction aa = (Fraction)a;
                Ordinary bb = (Ordinary)b;
                qStr.append(aa.generateStr()).append(" ").append(op.getSign()).append(" ").append(bb.getValue());
            } else {
                Ordinary aa = (Ordinary)a;
                Fraction bb = (Fraction)b;
                qStr.append(aa.getValue()).append(" ").append(op.getSign()).append(" ").append(bb.generateStr());
            }
        }
        if(hasBreaket) {
            qStr.append(")");
        }
    }

    /**
     * 获取一个随机类型的数
     */
    public Type getRandomTypeNum() {
        int code = random.nextInt(2) + 1;
        if( code == NumType.ORDINARY.getCode()) {
            Ordinary ordinary = new Ordinary();
            ordinary.getRandom(range);
            return ordinary;
        } else if(code == NumType.FRACTION.getCode()) {
            Fraction fraction = new Fraction();
            fraction.getRandom(range);
            return fraction;
        }
        return null;
    }

    /**
     * 随机生成运算符
     */
    public void getRandomOP() {
        for(int i = 0; i < numsCount - 1; i ++) {
            int code = random.nextInt(4) + 1;
            OP op = null;
            switch (code) {
                case 1: {
                    op = OP.ADD;
                    break;
                }
                case 2: {
                    op = OP.SUB;
                    break;
                }
                case 3: {
                    op = OP.MUL;
                    break;
                }
                case 4: {
                    op = OP.DIV;
                    break;
                }
            }
            nops.add(op);
        }
    }

    public Question(int range) {
        this.range = range;
    }

    public int getNumsCount() {
        return numsCount;
    }

    public void setNumsCount(int numsCount) {
        this.numsCount = numsCount;
    }

    public StringBuilder getqStr() {
        return qStr;
    }

    public void setqStr(StringBuilder qStr) {
        this.qStr = qStr;
    }

    public Type getAns() {
        return ans;
    }

    public void setAns(Type ans) {
        this.ans = ans;
    }

    public int getEquation() {
        return equation;
    }

    public void setEquation(int equation) {
        this.equation = equation;
    }

    public List<Type> getNums() {
        return nums;
    }

    public void setNums(List<Type> nums) {
        this.nums = nums;
    }

    public Queue<OP> getNops() {
        return nops;
    }

    public void setNops(Queue<OP> nops) {
        this.nops = nops;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
