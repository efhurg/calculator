package entity;

import utils.FileUtil;

import java.util.HashMap;

/**
 * 题目容器
 */
public class QuestionContainer {
    /** 容器id */
    private int id;
    /** 容器中题目容量 */
    private int capcity;
    /** 题目字符串 - 答案 */
    HashMap<String, Type> question2Ans;
    /** 题目输出文件路径 */
    private String questionPath;
    /** 答案输出文件路径 */
    private String ans;
    /** 题目数值范围指定 */
    private int range;

    /**
     * 将题目字符串与答案输出保存到指定路径
     */
    public void save() {
        FileUtil.outputFile(this);
    }

    /**
     * 生成指定数目的题目并存入容器
     */
    public void loadQuestions() {
        while(this.question2Ans.size() < this.capcity) {
            Question question = new Question(this.range);
            question.generateQuestion();

            String qStr = question.getqStr().toString();
            Type ans = question.getAns();
            // 对于两个数 1+2 与 2+1 重复情况进行特殊查重处理
            Boolean isRepeat = false;
            if(qStr.length() == 7) {
                char a = qStr.charAt(0), b = qStr.charAt(4);
                for (String str : question2Ans.keySet()) {
                        if(str.length() == 7 && (
                                (a == str.charAt(0) && b == str.charAt(4)) ||
                                (b == str.charAt(0) && a == str.charAt(4))
                        )) {
                            isRepeat = true;
                            break;
                        }
                }
            }
            if(isRepeat) continue;
            // 其他的若题目字符串相同则表示重复，在HashMap中会自动覆盖重复的题
            this.question2Ans.put(qStr, ans);
        }
    }

    public QuestionContainer() {
        this.question2Ans = new HashMap<>();
    }

    public QuestionContainer(int capcity, int range) {
        this.question2Ans = new HashMap<>();
        this.capcity = capcity;
        this.range = range;
    }

    public int getCapcity() {
        return capcity;
    }

    public void setCapcity(int capcity) {
        this.capcity = capcity;
    }

    public HashMap<String, Type> getQuestion2Ans() {
        return question2Ans;
    }

    public void setQuestion2Ans(HashMap<String, Type> question2Ans) {
        this.question2Ans = question2Ans;
    }
}
