package com.zjj.aisearch.utils;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.File;
import java.io.IOException;

public class pdfTo {

    public static void main(final String[] args) throws IOException, TikaException {
        parseMarkdown();

    }

    //解析pdf
    public static void parsePDF() throws IOException, TikaException {
        //选择要提取的文件
        File file = new File("G:\\百度网盘下载\\就业指导（79天）\\就业相关java\\[尚硅谷]_佟刚_Spring 面试题分析.pdf");

        //获取并打印文件内容
        Tika tika = new Tika();
        String filecontent = tika.parseToString(file);
        System.out.println("Extracted Content: " + filecontent);
    }

    //解析doc
    public static void parseDoc() throws IOException, TikaException {
        //选择要提取的文件
        File file = new File("H:\\BaiduDownload\\linux（26天）\\WEB26_Linux\\WEB26_Linux\\WEB26_Linux\\资料\\05-Vim命令合集.docx");

        //获取并打印文件内容
        Tika tika = new Tika();
        String filecontent = tika.parseToString(file);
        System.out.println("Extracted Content: " + filecontent);
    }
    //解析Excel
    public static void parseExcel() throws IOException, TikaException {
        //选择要提取的文件
        File file = new File("F:\\Desktop\\部门\\科创局\\重庆两江新区科创局政务信息资源目录.xlsx");

        //获取并打印文件内容
        Tika tika = new Tika();
        String filecontent = tika.parseToString(file);
        System.out.println("Extracted Content: " + filecontent);
    }
    //解析Java
    public static void parseJava() throws IOException, TikaException {
        //选择要提取的文件
        File file = new File("C:\\Users\\Administrator\\git\\java-design-patterns\\command\\src\\test\\java\\com\\iluwatar\\command\\AppTest.java");

        //获取并打印文件内容
        Tika tika = new Tika();
        String filecontent = tika.parseToString(file);
        System.out.println("Extracted Content: " + filecontent);
    }
    //解析Html
    public static void parseHtml() throws IOException, TikaException {
        //选择要提取的文件
        File file = new File("F:\\Desktop\\interesting\\网络\\天使轮、A轮、B轮、C轮、D轮融资 究竟是什么？_搜狐财经_搜狐网.html");

        //获取并打印文件内容
        Tika tika = new Tika();
        String filecontent = tika.parseToString(file);
        System.out.println("Extracted Content: " + filecontent);
    }
    //解析Markdown
    public static void parseMarkdown() throws IOException, TikaException {
        //选择要提取的文件
        File file = new File("I:\\c盘下载\\VimDesktop\\doc\\wiki\\全局快捷键列表（字母顺序）.md");

        //获取并打印文件内容
        Tika tika = new Tika();
        String filecontent = tika.parseToString(file);
        System.out.println("Extracted Content: " + filecontent);
    }

}