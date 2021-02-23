package com.interceptor;

import com.entity.Admin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class MyBackInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin!=null){
            actionInvocation.invoke();
            return null;
        }else {
            return "login";
        }

    }
}
