package utils;

import constant.NumType;
import entity.Fraction;
import entity.Ordinary;
import entity.QuestionContainer;
import entity.Type;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件工具类
 */
public class FileUtil {
    /** 输出题目题目和答案文件 **/
    public static void outputFile(QuestionContainer container){
        FileWriter wir1 = null;
        FileWriter wir2 = null;

        try {
            // 输出文件夹路径，创建输出文件夹对象
            String url = "src/output/";
            File dir = new File(url);
            dir.mkdirs();

            wir1 = new FileWriter("src/output/Exercises.txt");
            wir2 = new FileWriter("src/output/Answers.txt");
            PrintWriter pri1 = new PrintWriter(wir1, false);
            PrintWriter pri2 = new PrintWriter(wir2, false);

            HashMap<String, Type> question2Ans = container.getQuestion2Ans();

            int i = 0;
            for (Map.Entry<String, Type> entry : question2Ans.entrySet()) {
                ++i;
                String qStr = entry.getKey();
                Type ans = entry.getValue();
                String ansStr = "?";
                NumType numType = ans.getNumType();
                if(numType.getCode() == NumType.ORDINARY.getCode()) {
                    Ordinary oAns = (Ordinary) ans;
                    ansStr = String.valueOf(oAns.getValue());
                } else if(numType.getCode() == NumType.FRACTION.getCode()) {
                    Fraction fAns = (Fraction) ans;
                    ansStr = fAns.generateStr();
                }

                pri1.print(i + ". " + qStr + "\n");
                pri2.print(i + ". " + ansStr + "\n");
            }

            wir1.close();
            wir2.close();
            pri1.close();
            pri2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 输出结果文件 **/
    public static void CorrectingOutput(File queFile, File ansFile){
        // 读取题目文件和答案文件进行比对
        StringBuilder que = new StringBuilder();
        StringBuilder ans = new StringBuilder();

        try {
            BufferedReader read1 = new BufferedReader(new FileReader(queFile));
            BufferedReader read2 = new BufferedReader(new FileReader(ansFile));

            StringBuilder correct = new StringBuilder();
            StringBuilder wrong = new StringBuilder();
            int corCount = 0;
            int wroCount = 0;

            String str1 = null;
            String str2 = null;


            while ((str1 = read1.readLine()) != null){
                str2 = read2.readLine();
                if(str2 == null ||str2.length() == 0 || str1.length() == 0) continue;

                int eqIndex = str1.indexOf("=");
                String temp  = str1.substring(eqIndex+1);
                String a = temp.trim();

                int eqIndex2 = str2.indexOf(".");
                String temp2  = str2.substring(eqIndex2 +1);
                String b = temp2.trim();

                // 分割后若相等则把 str1 的第一个字符，即题号提出出来存进 correct 中
                // 反之则存进 wrong 中
                try {
                    if (a.equals(b)){
                        correct.append(String.valueOf(str1.substring(0, str1.indexOf(".")))).append(", ");
                        corCount++;
                    } else {
                        wrong.append(String.valueOf(str1.substring(0, str1.indexOf(".")))).append(", ");
                        wroCount++;
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("文件格式不正确!");
                    return;
                }

            }

            // 剪切掉两个字符串冗余的字符
            if (correct.length() > 2){
                correct.delete(correct.length() - 2, correct.length());
            }
            if (wrong.length() > 2){
                wrong.delete(wrong.length() - 2, wrong.length());
            }

            FileWriter wir1 = null;
            wir1 = new FileWriter(queFile.getParent() + "/Grade.txt");
            PrintWriter pri1 = new PrintWriter(wir1, false);

            pri1.print("Correct: " + corCount + " (" + correct + ")" + "\n");
            pri1.print("Wrong: " + wroCount + " (" + wrong + ")");

            pri1.close();
            wir1.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




