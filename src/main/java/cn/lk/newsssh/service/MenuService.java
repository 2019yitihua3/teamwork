package cn.lk.newsssh.service;

import cn.lk.newsssh.bean.Cmenu;
import cn.lk.newsssh.dao.BaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
	@Resource
	private BaseDao<Cmenu>dao;
	
	public List<Cmenu> listMenu(int pid){
		return dao.find("from Cmenu cmenu where cmenu.pid=?",new Object[]{pid});
	}
	

}
