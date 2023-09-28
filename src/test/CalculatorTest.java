package test;

import constant.NumType;
import entity.Fraction;
import entity.Ordinary;
import entity.Type;
import org.junit.Test;
import utils.Calculator;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void f() {
    }

    @Test
    public void calculateNumer() {
    }

    @Test
    public void calculateDenomin() {
    }

    @Test
    public void testCalculateNumer() {
    }

    @Test
    public void ooCalculate() {
    }

    @Test
    public void foCalculate() {
        Fraction fraction = new Fraction();
        fraction.setHeadNum(8);
        fraction.setNumerator(8);
        fraction.setDenominator(9);
        Ordinary ordinary = new Ordinary();
        ordinary.setValue(8);
        Type ans = Calculator.calculate(fraction, ordinary, 3);
        if(ans.getNumType().getCode() == NumType.FRACTION.getCode()) {
            Fraction fAns = (Fraction) ans;
            System.out.println(fAns.getHeadNum() + "'" + fAns.getNumerator() + "/" + fAns.getDenominator());
        } else {
            Ordinary oAns = (Ordinary) ans;
            System.out.println(oAns.getValue());
        }
    }

    @Test
    public void ofCalculate() {
    }

    @Test
    public void ffCalculate() {
        Fraction fraction1 = new Fraction();
        fraction1.setHeadNum(6);
        fraction1.setNumerator(5);
        fraction1.setDenominator(7);

        Fraction fraction2 = new Fraction();
        fraction2.setHeadNum(10);
        fraction2.setNumerator(4);
        fraction2.setDenominator(8);

        Fraction midres = (Fraction) Calculator.calculate(fraction1, fraction2, 3);

        Ordinary ordinary = new Ordinary();
        ordinary.setValue(10);

        Ordinary midres1 = (Ordinary) Calculator.calculate(midres, ordinary, 3);

        Ordinary ordinary1 = new Ordinary();
        ordinary1.setValue(10);

        Type ans = Calculator.calculate(midres1, ordinary1, 1);

        if(ans.getNumType().getCode() == NumType.FRACTION.getCode()) {
            Fraction fAns = (Fraction) ans;
            System.out.println(fAns.getHeadNum() + "'" + fAns.getNumerator() + "/" + fAns.getDenominator());
        } else {
            Ordinary oAns = (Ordinary) ans;
            System.out.println(oAns.getValue());
        }
    }
}