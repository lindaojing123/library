package com.web;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;
import com.service.UserServiceImpl;
import com.util.MD5Util;
import com.util.UUIDUtil;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
public class UserAction extends ActionSupport {
    User user;
    String code;
    String uid;
    String password;
    String email;
    UserService service = new UserServiceImpl();
    public String show(){
        List<User> userList = service.getAllUser();
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("users",userList);
        return "show";
    }

    public String update(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String id = request.getParameter("userId");
        int id1 = Integer.parseInt(id);
        String s = request.getParameter("status");
        int status = Integer.parseInt(s);
        service.updateStatus(status,id1);
        return "update";
    }


    public String verifyForm() {

        String str = UUIDUtil.get12UUID();

        user.setActiveCode(str);
        user.setRegistTime(new Date());
        boolean flag = false;
        List<User> users = service.getAllUser();
        for(User u : users){
            System.out.println(u.getEmail());
            if(u.getEmail().equals(user.getEmail())){
                flag = true;
            }
        }
        if(flag==false){
            System.out.println("flag"+flag);
            service.register(user);
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("activeCode", str);
            request.setAttribute("email", user.getEmail());
            request.setAttribute("uid", user.getId());
            return "ok";
        }else {
            return "false";
        }

    }

    public String registerOk(){
        User user = service.selectOneById(uid);
        String passwordAndSalt = MD5Util.getStringMD5(user.getPassword()+user.getActiveCode());
        service.updateUser(passwordAndSalt,uid);
        HttpServletRequest request = ServletActionContext.getRequest();
        if(user.getActiveCode().equals(code)){
              service.updateStatus(user.getStatus(),Integer.parseInt(uid));
              request.setAttribute("user",user);
              return "registerOk";
        }else {
              return "error";
        }
    }

    public String login(){
        User user = service.getUserByEmail(email);
        String passwordAndSalt = MD5Util.getStringMD5(password+user.getActiveCode());
        user = service.login(email,passwordAndSalt);
        if(user!=null){
            if(user.getStatus()==1){
                HttpSession session = ServletActionContext.getRequest().getSession();
                session.setAttribute("user",user);
                return "success";
            }
        }
        return "login";
    }

    public String leaveOut(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        if(session.getAttribute("user") != null){
            session.removeAttribute("user");
        }
        return "leaveOut";
    }
    public String checkName(){

        return "checkName";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
