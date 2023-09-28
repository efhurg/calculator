package test;

import constant.NumType;
import entity.Fraction;
import entity.Ordinary;
import entity.QuestionContainer;
import entity.Type;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class QuestionContainerTest {

    @Test
    public void save() {
        QuestionContainer container = new QuestionContainer(10000, 10);
        container.loadQuestions();
        HashMap<String, Type> question2Ans = container.getQuestion2Ans();
        for (Map.Entry<String, Type> entry : question2Ans.entrySet()) {
            Type ans = entry.getValue();
            if(ans.getNumType().getCode() == NumType.FRACTION.getCode()) {
                Fraction fAns = (Fraction) ans;
            } else {
                Ordinary oAns = (Ordinary) ans;
            }
        }
        container.save();
    }

    @Test
    public void loadQuestions() {
        QuestionContainer container = new QuestionContainer(10000, 10);
        container.loadQuestions();
        HashMap<String, Type> question2Ans = container.getQuestion2Ans();
        for (Map.Entry<String, Type> entry : question2Ans.entrySet()) {
            System.out.print(entry.getKey());
            Type ans = entry.getValue();
            if(ans.getNumType().getCode() == NumType.FRACTION.getCode()) {
                Fraction fAns = (Fraction) ans;
                System.out.println(" " + fAns.generateStr());
            } else {
                Ordinary oAns = (Ordinary) ans;
                System.out.println(" " + oAns.getValue());
            }
        }
    }
}