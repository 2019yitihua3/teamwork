package cn.lk.newsssh.service;

import cn.lk.newsssh.bean.News;
import cn.lk.newsssh.bean.User;
import cn.lk.newsssh.dao.BaseDao;
import cn.lk.newsssh.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private BaseDao<User> dao;


    public User findUserByUidAndPwd(String uid, String pwd) {
        pwd=MyUtils.lkCode(pwd, "");
        return dao.get(" from User u where u.uid = ? and u.pwd = ? ", new Object[] { uid,  pwd});
    }

    public Long getCount(){
    	return dao.count("select count(*) from User");
    }

    public void addUser(User user) {
        dao.save(user);
    }
    /////////////查///////////////////////////////////////////////////////
    //按分页查询
    public List<User> listDgUser(String xm, int page, int rows){
        if(xm == null || "".equals(xm))
            return dao.find("from User", new Object[0], page, rows);
        else
            return dao.find("from User WHERE xm like ?", new Object[]{'%' +xm+'%'}, page, rows);
    }
    /////////////删///////////////////////////////////////////////////////
    public void deleteUser(String uid,Class<User> user) throws Exception{
        User u =(User) dao.get(user, uid);
        dao.delete(u);
    }
    //按uid查询
    public User getUser(Class<User> clazz, String uid){
        User user=dao.get(clazz, uid);
        return  user;
    }
    /////////////改///////////////////////////////////////////////////////
    public void updateUser(User user) throws Exception{
        dao.update(user);
    }

}