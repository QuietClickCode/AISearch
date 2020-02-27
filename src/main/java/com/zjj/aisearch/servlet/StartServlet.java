package com.zjj.aisearch.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zjj.aisearch.demo.test.TestScan;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "startServlet", urlPatterns = "/*")  //标记为servlet，以便启动器扫描。
@Slf4j
public class StartServlet extends HttpServlet {

    /**
     * 初始化项目
     * 1：获取Servlet名称，加载名称相同的配置文件
     * 2：加载配置文件中的urlMapping
     * 3：加载其他配置
     */

    /**
     * 2020年2月27日23:38:51
     * 项目启动自动加载这个方法,只加载一次
     * 这个时候把注解和对应的执行方法读进去
     *
     * @param config
     */
    @Override
    @SneakyThrows(ServletException.class)
    public void init(ServletConfig config) {
        super.init(config);
        //为了xml配置映射,其实用注解完全可以不要
        String servletName = config.getServletName();
        log.info("【 zjjMVC " + servletName + " starting 。。。】");
        //--------------------
        //扫描注解,装配url和method的mapping



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            doInvoke(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doInvoke(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        Object methodAnnotation = TestScan.getMethodAnnotation(requestURL);
        System.out.println(methodAnnotation + "=======");
        response.setHeader("content-type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(methodAnnotation));
        writer.close();
    }

}