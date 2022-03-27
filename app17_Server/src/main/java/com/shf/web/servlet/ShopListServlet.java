package com.shf.web.servlet;

import com.google.gson.Gson;
import com.shf.web.bean.ShopInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 返回json格式的ShopList的Servlet
 */
public class ShopListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        List<ShopInfo> list = getAllShops(request);
        String json = new Gson().toJson(list);
        System.out.println(json);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }

    /*
     * 得到所有商品信息对象的集合
     */
    private List<ShopInfo> getAllShops(HttpServletRequest request) throws UnknownHostException {
        // 准备一个空集合
        List<ShopInfo> list = new ArrayList<ShopInfo>();
        // 得到images文件夹的真实路径
        String imagesPath = getServletContext().getRealPath("/images");
        // 创建images文件夹File对象
        File file = new File(imagesPath);
        // 得到images文件夹下所有图片文件的file对象数组
        File[] files = file.listFiles();
        // 遍历
        for (int i = 0; i < files.length; i++) {
            // 得到商品的相关信息
            int id = i + 1;
            String imageName = files[i].getName();
            String name = imageName.substring(0, imageName.lastIndexOf("."))
                    + "的商品名称";
            String imagePath = "http://" + InetAddress.getLocalHost().getHostAddress() + ":"
                    + request.getLocalPort() + "/" + request.getContextPath()
                    + "/images/" + imageName;
            float price = new Random().nextInt(20) + 20;
            // 封装成对象
            ShopInfo info = new ShopInfo(id, name, imagePath, price);
            // 添加到集合中
            list.add(info);
        }
        return list;
    }

}
