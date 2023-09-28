package main;

import entity.QuestionContainer;
import utils.FileUtil;

import java.io.File;
import java.util.Scanner;

/**
 * 程序入口类
 */
public class Application {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("********欢迎使用四则运算题目生成与批改程序*********");
        System.out.println("************您有以下功能可以进行选择*************");
        System.out.println("               0. 退出程序                    ");
        System.out.println("               1. 生成题目                    ");
        System.out.println("               2. 批改题目                    ");
        while(true) {
            System.out.print("请输入您想要使用的功能序号：");
            int i = Integer.parseInt(sc.nextLine());
            switch (i) {
                case 0 : {
                    System.out.println("程序已退出，欢迎下次使用！");
                    System.exit(0);
                }
                case 1 : {
                    gQuestionMenu();
                    break;
                }
                case 2 : {
                    correctMenu();
                    break;
                }
                default: {
                    System.out.println("输入错误，请重新输入！");
                }
            }
        }
    }

    /**
     * 生成题目函数
     */
    public static void gQuestionMenu() {
        System.out.print("请输入生成题目的个数：");
        int n = Integer.parseInt(sc.nextLine());
        System.out.print("请输入题目的取值范围：");
        int r = Integer.parseInt(sc.nextLine());
        QuestionContainer container = new QuestionContainer(n, r);
        // 生成题目与答案
        container.loadQuestions();
        // 保存题目与答案
        container.save();
        System.out.println("已成功保存题目文本Exercises.txt和答案文本Answers.txt");
    }

    /**
     * 批改题目函数
     */
    public static void correctMenu() {
        System.out.print("请输入题目文本的绝对路径：");
        String testTxt = sc.nextLine();
        System.out.print("请输入答案文本的绝对路径：");
        String answersTxt = sc.nextLine();
        FileUtil.CorrectingOutput(new File(testTxt), new File(answersTxt));
        System.out.println("题目批改结果已成功保存至Grade文本！");
    }
}
