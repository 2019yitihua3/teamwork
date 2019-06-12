package cn.lk.newsssh.action;

import cn.lk.newsssh.bean.News;
import cn.lk.newsssh.bean.User;
import cn.lk.newsssh.service.UserService;
import cn.lk.newsssh.utils.GsonUtils;
import cn.lk.newsssh.utils.MyUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//自动根据bean的类名实例化一个首写字母为小写的bean，如userAct
public class UserAct extends ActionSupport {

    @Resource  //把service注入到action中,不需要new
    private UserService userService;
    private User user;
    private int page,rows,id;//分页参数,来源于前端
    private String xm; //查询的关键词：新闻标题,来源于前端
    private String uid,pwd; //属性值来源于前端同名传输来的数据
    private String jsonResult;
    private HashMap<String,Object> jsonobj=new HashMap<String,Object>();
    //INPUT 和 SUCCESS是actionsupport类提供的两个返回常量。
    //INPUT默认用来返回输入异常，SUCCESS默认是处理数据完成，成功跳转。
    //INPUT从哪里来就回到哪里去？例如登录页面，进入后台后返回input,那就是继续回到登录页面。
    public String doLogin(){ 
        try { 
            HttpServletRequest request = ServletActionContext.getRequest();
            User user = userService.findUserByUidAndPwd(uid, pwd);
            jsonobj.clear();
            if(user != null){ 
                jsonobj.put("ok", true);
                jsonobj.put("msg", "goIndex");
                ActionContext actionContext = ActionContext.getContext();
                Map<String,Object>session = actionContext.getSession(); 
                session.put("me", user);
            }else 
                {
                jsonobj.put("ok", false);
                jsonobj.put("msg", "用户获取密码错误");
                }
        } catch (Exception e) { 
            jsonobj.put("ok", false);
            jsonobj.put("msg", "系统错误2"); 
        } 
        jsonResult = GsonUtils.toJson(jsonobj);
        HttpServletResponse response = ServletActionContext.getResponse();
        MyUtils.outPrint(response, jsonResult);
        return null;
    } 
    public String doLogout(){
        ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        session.put("me", null);
        return "logout";
    }
    //请求跳转到新闻管理页
    public String goIndex(){
        return "goadmin";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    //请求跳转到查看用户
    public String goListUser(){
        return "golistuser";
    }
    //请求跳转到添加用户
    public String goAddUser(){
        return "goadduser";
    }
    //保存添加的新闻
    public String saveAddUser(){
        jsonobj.clear();
        try {
            user.setRole("2");
            user.setPwd(MyUtils.lkCode(user.getPwd(), null) );
            userService.addUser(user);
            jsonobj.put("ok", true);
            jsonobj.put("msg", "goadmin");
        } catch (Exception e) {
            e.printStackTrace();
            jsonobj.put("ok", false);
            jsonobj.put("msg", "系统错误");
        }
        jsonResult = GsonUtils.toJson(jsonobj);
        HttpServletResponse response = ServletActionContext.getResponse();
        MyUtils.outPrint(response, jsonResult);
        return null;
    }
    //保存修改后的用户
    public String saveEditUser(){
        jsonobj.clear();
        try {
            User user1=userService.getUser(User.class, user.getUid());
            user1.setBj(user.getBj());
            user1.setPwd(MyUtils.lkCode(user.getPwd(), null));
            if("超级管理员".equals(user.getRole())){
                user1.setRole("1");
            }else{
                user1.setRole("2");
            }
            user1.setXm(user1.getXm());
            userService.updateUser(user1);
            jsonobj.put("ok", true);
            jsonobj.put("msg", "goadmin");
        } catch (Exception e) {
            e.printStackTrace();
            jsonobj.put("ok", false);
            jsonobj.put("msg", "系统错误2");
        }
        jsonResult = GsonUtils.toJson(jsonobj);
        HttpServletResponse response = ServletActionContext.getResponse();
        MyUtils.outPrint(response, jsonResult);
        return null;
    }
    //分页查询用户
    public String listUser(){
        List<User> userlist=userService.listDgUser(xm,page,rows);
        jsonobj.clear();
        jsonobj.put("total", userlist.size());
        jsonobj.put("rows", userlist);
        jsonResult = MyUtils.toJson(jsonobj);
        HttpServletResponse response =ServletActionContext.getResponse();
        MyUtils.outPrint(response, jsonResult);
        return null;
    }
    ///////////////////删/////////////////////////////////////////////////////////
    //删除一个用户
    public String doDel1User(){
        jsonobj.clear();
        boolean delflag=false;
        try{
            userService.deleteUser(uid, User.class);
            delflag=true;
        }catch(Exception e){
            e.printStackTrace();
            delflag=false;
        }
        jsonobj.put("delflag", delflag);
        HttpServletResponse response = ServletActionContext.getResponse();
        MyUtils.outPrint(response, GsonUtils.toJson(jsonobj));
        return null;
    }
    //请求跳转到修改用户
    public String goEditUser(){
        user=userService.getUser(User.class, uid);
        if("1".equals(user.getRole())){
            user.setRole("超级管理员");
        }else{
            user.setRole("普通管理员");
        }
        return "goedituser";
    }
}