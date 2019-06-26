package cn.lk.newsssh.action;

import cn.lk.newsssh.bean.Ntype;
import cn.lk.newsssh.bean.Role;
import cn.lk.newsssh.service.NtypeService;
import cn.lk.newsssh.service.RoleService;
import cn.lk.newsssh.utils.GsonUtils;
import cn.lk.newsssh.utils.MyUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @author BushRo
 * @Description: TOTO
 * @date 2019-06-16
 */
@Controller("roleAct")
public class RoleAct extends ActionSupport {
    @Resource
    private RoleService roleService;
    @Resource
    private NtypeService ntypeService;
    private String jsonResult;
    private int page,rows,id;//分页参数,来源于前端
    private String pstr; //属性值来源于前端同名传输来的数据
    private HashMap<String,Object> jsonobj=new HashMap<String,Object>();
    private Role role;

    public Role getRole() {
        return role;
    }

    public String getPstr() {
        return pstr;
    }

    public void setPstr(String pstr) {
        this.pstr = pstr;
    }

    public void setRole(Role role) {
        this.role = role;
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

    //请求跳转到修改类型页
    public String findListRole(){
        List<Role> list = roleService.findList();
        System.out.println(list.size());
        jsonResult = MyUtils.toJson(list);
        HttpServletResponse response = ServletActionContext.getResponse();
        MyUtils.outPrint(response, jsonResult);
        return null;
    }
    //请求跳转到查看类型
    public String goListRole(){
        return "golistrole";
    }
    //请求跳转到添加类型
    public String goAddRole(){
        return "goaddrole";
    }
    //保存添加的类型
    public String saveAddRole(){
        jsonobj.clear();
        try {
            Role role1 = roleService.fingByRidAndPower(this.role.getRid(), this.role.getPower());
            if(null==role1){
                if(this.role.getRid()==1){
                    this.role.setRstr("超级管理员");
                }else{
                    this.role.setRstr("普通管理员");
                }
                List<Ntype> list = ntypeService.findList();
                String pstr=null;
                for(int i=0;i<list.size();i++){
                    if(this.role.getPower()==list.get(i).getId()){
                        pstr=list.get(i).getTypename();
                        break;
                    }
                }
                this.role.setPstr(pstr);
                roleService.addRole(this.role);
                jsonobj.put("ok", true);
                jsonobj.put("msg", "goadmin");

            }else{
                jsonobj.put("ok", false);
                jsonobj.put("msg", "该角色已经可以访问该栏目了");
            }
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
    //保存修改后的类型
    public String saveEditRole(){
        jsonobj.clear();
        try {
            roleService.updateRole(role);
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
    //分页查询类型
    public String listRole(){
        List<Role> rolelist=roleService.listDgRole(pstr,page,rows);
        jsonobj.clear();
        jsonobj.put("total", rolelist.size());
        jsonobj.put("rows", rolelist);
        jsonResult = MyUtils.toJson(jsonobj);
        HttpServletResponse response =ServletActionContext.getResponse();
        MyUtils.outPrint(response, jsonResult);
        return null;
    }
    ///////////////////删/////////////////////////////////////////////////////////
    //删除一个类型
    public String doDel1Role(){
        jsonobj.clear();
        boolean delflag=false;
        try{
            roleService.deleteRole(id, Role.class);
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
    //请求跳转到修改类型
    public String goEditRole(){
        role=roleService.getRole(Role.class, id);
        return "goeditrole";
    }

}
