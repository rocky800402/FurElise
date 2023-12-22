package com.furelise.emp.model;

import java.util.List;

public interface EmpDAO {

	void add(Emp emp);
	void update(Emp emp);
	void delete(Integer empID);
	Emp findByPK(Integer empID); //用PK回傳一個夥伴資料
	List<Emp> getAll();
}
