package cn.lk.newsssh.action;

import cn.lk.newsssh.bean.News;
import cn.lk.newsssh.bean.Ntype;
import cn.lk.newsssh.bean.Ntype;
import cn.lk.newsssh.bean.User;
import cn.lk.newsssh.service.NtypeService;
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
@Controller("typeAct")
public class NtypeAct extends ActionSupport {
    @Resource
    private NtypeService ntypeService;
    private String jsonResult;
    private int page,rows,id;//分页参数,来源于前端
    private String typename; //属性值来源于前端同名传输来的数据
    private HashMap<String,Object> jsonobj=new HashMap<String,Object>();
    private Ntype ntype;

    public Ntype getNtype() {
        return ntype;
    }

    public void setNtype(Ntype ntype) {
        this.ntype = ntype;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
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
    public String findListType(){
        List<Ntype> list = ntypeService.findList();
        System.out.println(list.size());
        jsonResult = MyUtils.toJson(list);
        HttpServletResponse response = ServletActionContext.getResponse();
        MyUtils.outPrint(response, jsonResult);
        return null;
    }
    //请求跳转到查看类型
    public String goListType(){
        return "golisttype";
    }
    //请求跳转到添加类型
    public String goAddType(){
        return "goaddtype";
    }
    //保存添加的类型
    public String saveAddType(){
        jsonobj.clear();
        try {
            ntypeService.addNtype(ntype);
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
    //保存修改后的类型
    public String saveEditType(){
        jsonobj.clear();
        try {
            ntypeService.updateNtype(ntype);
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
    public String listType(){
        List<Ntype> typelist=ntypeService.listDgNtype(typename,page,rows);
        jsonobj.clear();
        jsonobj.put("total", typelist.size());
        jsonobj.put("rows", typelist);
        jsonResult = MyUtils.toJson(jsonobj);
        HttpServletResponse response =ServletActionContext.getResponse();
        MyUtils.outPrint(response, jsonResult);
        return null;
    }
    ///////////////////删/////////////////////////////////////////////////////////
    //删除一个类型
    public String doDel1Type(){
        jsonobj.clear();
        boolean delflag=false;
        try{
            ntypeService.deleteNtype(id, Ntype.class);
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
    public String goEditType(){
        ntype=ntypeService.getNtype(Ntype.class, id);
        return "goedittype";
    }

}
