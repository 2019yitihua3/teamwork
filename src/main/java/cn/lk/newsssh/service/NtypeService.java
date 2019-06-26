package cn.lk.newsssh.service;

import cn.lk.newsssh.bean.Ntype;
import cn.lk.newsssh.bean.Ntype;
import cn.lk.newsssh.dao.BaseDao;
import cn.lk.newsssh.utils.MyUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NtypeService {
	@Resource
	private BaseDao<Ntype>dao;

	//查找所有类别
	public List<Ntype> findList(){
		return dao.find("from Ntype");
	}
	//根据id查找类别
	public Ntype fingByid(int id){
		return dao.get("from Ntype ntype where id=",new Object[]{id});
	}


	public Long getCount(){
		return dao.count("select count(*) from Ntype");
	}

	public void addNtype(Ntype type) {
		dao.save(type);
	}
	/////////////查///////////////////////////////////////////////////////
	//按分页查询
	public List<Ntype> listDgNtype(String typename, int page, int rows){
		if(typename == null || "".equals(typename))
			return dao.find("from Ntype", new Object[0], page, rows);
		else
			return dao.find("from Ntype WHERE typename like ?", new Object[]{'%' +typename+'%'}, page, rows);
	}
	/////////////删///////////////////////////////////////////////////////
	public void deleteNtype(int id,Class<Ntype> type) throws Exception{
		Ntype u =(Ntype) dao.get(type, id);
		dao.delete(u);
	}
	//按id查询
	public Ntype getNtype(Class<Ntype> clazz, int id){
		Ntype type=dao.get(clazz, id);
		return  type;
	}
	/////////////改///////////////////////////////////////////////////////
	public void updateNtype(Ntype type) throws Exception{
		dao.update(type);
	}
}
