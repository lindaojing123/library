package com.web;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;
import com.service.UserServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CheckAction extends ActionSupport {

    UserService service = new UserServiceImpl();
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String email = request.getParameter("email");
        boolean b = true;
        String result = null;
        List<User> users = service.getAllUser();
        for(User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
               b=false;
            }
        }
        if(b){
            result = "<font color='green'>该用户还未被使用</font>";
            System.out.println(result );
        }else {
            result = "<font color='red'>该用户已经被使用</font>";
            System.out.println(result );
        }
        ServletActionContext.getResponse().setContentType("text/html");
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        ServletActionContext.getResponse().getWriter().print(result);
        return null;
    }
}
