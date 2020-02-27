package com.zjj.aisearch.test;

import java.io.*;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2020-02-25 00:19:58
 **/
public class FileTest {
    public static void main(String[] args) throws IOException {
        removePoint();
    }

    //操作文件,去空行
    private static void removeLine() throws IOException {
        File file = new File("F:\\AISearch\\src\\main\\resources\\haha.txt");
        File file2 = new File("F:\\AISearch\\src\\main\\resources\\hehe.txt");
        FileReader fileReader = new FileReader(file);
        FileWriter fileWriter = new FileWriter(file2);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            while (bufferedReader.readLine() != null) {
                String s = bufferedReader.readLine();
                if (s.length() != 0) {
                    System.out.println(s + "----");
                    bufferedWriter.write(s + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void removePoint() throws IOException {
        File file = new File("F:\\AISearch\\src\\main\\resources\\hehe.txt");
        File file2 = new File("F:\\AISearch\\src\\main\\resources\\xixi.txt");
        FileReader fileReader = new FileReader(file);
        FileWriter fileWriter = new FileWriter(file2);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            char inss = (char) bufferedReader.read();
            String s = String.valueOf(inss);
            String ss = String.valueOf((char) bufferedReader.read());
            while (String.valueOf((char) bufferedReader.read()) != null) {
                System.out.println(String.valueOf((char) bufferedReader.read()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
