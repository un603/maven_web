package com.hit.web;

import com.google.gson.Gson;
import com.hit.pojo.User;
import com.hit.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    /**
     * @Author DELL
     * @Description 处理登录的功能
     * @Date 2021/7/26 15:36
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        // 调用 userService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));

        // 如果等于 null,说明登录 失败!
        if (loginUser == null) {
            //把错误信息，和回显的表单项信息，保存到request域中
            request.setAttribute("msg", "用户名或密码错误！");
            request.setAttribute("username", username);
            request.setAttribute("password", password);


            //跳回登录页面
            System.out.println("登陆失败");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            //登陆成功
            //跳回成功登录页面login_success.html
            System.out.println("登陆成功");

            request.getSession().setAttribute("user", loginUser);

            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }

    /**
     * @Author DELL
     * @Description 处理注册的功能
     * @Date 2021/7/26 15:36
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //獲取Session中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

//        User user = WEBUtils.copyParamToBean(request.getParameterMap(), new User());

        //2.检查 验证码是否正确 === 写死,要求验证码为:abcde
        if (token != null && token.equalsIgnoreCase(code)) {

            //3.检查用户名是否可用
            if (userService.existsUsername(username)) {

                System.out.println("用户名[" + username + "]已存在!");
                //把回显信息，保存到request域中
                request.setAttribute("msg", "用户名已存在！");
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("email", email);
                request.setAttribute("code", code);

                //跳回注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                //调用service保存到数据库
                userService.registerUser(new User(null, username, password, email));
                //跳到注册成功页面regist_success.html
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);

            }

        } else {
            //把回显信息，保存到request域中
            request.setAttribute("msg", "验证码错误！");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.setAttribute("code", code);


            //不正确
            System.out.println("验证码[" + code + "]错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    /**
     * @Author DELL
     * @Description  处理注销的功能
     * @Date 2021/7/31 15:05
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 1、销毁 Session 中用户登录的信息（或者销毁 Session）
        request.getSession().invalidate();

        // 2、重定向到首页（或登录页面）。
        response.sendRedirect(request.getContextPath());
    }

    /**
     * @Author DELL
     * @Description
     * @Date 2021/8/3 18:09
     * @Param [request, response]
     * @Return void
     * @Version 1.0
     */
    protected void ajaxExistUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取请求参数
        String username = request.getParameter("username");

        boolean existsUsername = userService.existsUsername(username);

        //把返回的结果封装成为map对象
        Map<String , Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        response.getWriter().write(json);

    }
}
