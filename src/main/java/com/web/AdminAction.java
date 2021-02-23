package com.web;

import com.entity.Admin;
import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AdminService;
import com.service.AdminServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminAction extends ActionSupport {
    private String username;
    private String password;
    private String code;


    AdminService service = new AdminServiceImpl();

    public String login(){
        Admin admin = service.login(username,password);
        HttpSession session = ServletActionContext.getRequest().getSession();
        String msg = (String) session.getAttribute("security_code_key");
        if( msg.equals(code)  && admin != null ){
            session.setAttribute("admin",admin);
            return "success";
        }else {
            return "error";
        }

    }

    public String exit(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        return "success";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
