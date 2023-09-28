package test;

import constant.NumType;
import entity.Fraction;
import entity.Ordinary;
import entity.Question;
import entity.Type;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {

    @Test
    public void generateQuestion() {
        Question q = new Question(10);
        q.generateQuestion();
        System.out.println("question：" + q.getqStr());
        Type ans = q.getAns();
        if(ans.getNumType() == NumType.ORDINARY) {
            Ordinary oAns = (Ordinary) ans;
            System.out.println("ans：" + oAns.getValue());
        } else {
            Fraction fAns = (Fraction) ans;
            System.out.println("ans：" + fAns.generateStr());
        }
    }

    @Test
    public void twoNumEquationCal() {
    }

    @Test
    public void swap() {
        Ordinary a = new Ordinary();
        a.setValue(1);
        Ordinary b = new Ordinary();
        b.setValue(2);
        System.out.println("a：" + a.getValue() + "b：" + b.getValue());
        Question q = new Question(10);
        Ordinary temp = a;
        a = b;
        b = temp;
        System.out.println("a：" + a.getValue() + "b：" + b.getValue());
    }

    @Test
    public void cal() {
    }

    @Test
    public void generateEquationStr() {
    }

    @Test
    public void getRandomTypeNum() {
    }

    @Test
    public void getRandomOP() {
    }

    @Test
    public void getNumsCount() {
    }

    @Test
    public void setNumsCount() {
    }

    @Test
    public void getqStr() {
    }

    @Test
    public void setqStr() {
    }

    @Test
    public void getAns() {
    }

    @Test
    public void setAns() {
    }
}