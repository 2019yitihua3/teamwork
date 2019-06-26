package cn.lk.newsssh.service;

import cn.lk.newsssh.bean.Role;
import cn.lk.newsssh.dao.BaseDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
	@Resource
	private BaseDao<Role>dao;

	//查找所有类别
	public List<Role> findList(){
		return dao.find("from Role");
	}
	//根据id查找类别
	public Role fingByid(int id){
		return dao.get("from Role where id=",new Object[]{id});
	}
	//根据rid,poser查找类别
	public Role fingByRidAndPower(int rid,int power){
		return dao.get("from Role where rid = ? and power = ?",new Object[]{rid,power});
	}
	//根据rid查找类别
	public List<Role> fingByRid(int rid){
		return dao.find("from Role where rid=?", new Object[]{rid});
	}

	public Long getCount(){
		return dao.count("select count(*) from Role");
	}

	public void addRole(Role role) {
		dao.save(role);
	}
	/////////////查///////////////////////////////////////////////////////
	//按分页查询
	public List<Role> listDgRole(String pstr, int page, int rows){
		if(pstr == null || "".equals(pstr))
			return dao.find("from Role", new Object[0], page, rows);
		else
			return dao.find("from Role WHERE pstr like ?", new Object[]{'%' +pstr+'%'}, page, rows);
	}
	/////////////删///////////////////////////////////////////////////////
	public void deleteRole(int id,Class<Role> role) throws Exception{
		Role u =(Role) dao.get(role, id);
		dao.delete(u);
	}
	//按id查询
	public Role getRole(Class<Role> clazz, int id){
		Role role=dao.get(clazz, id);
		return  role;
	}
	/////////////改///////////////////////////////////////////////////////
	public void updateRole(Role role) throws Exception{
		dao.update(role);
	}
}
