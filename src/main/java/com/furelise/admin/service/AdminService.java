package com.furelise.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furelise.admin.model.dto.AdminLoginDTO;
import com.furelise.admin.model.entity.Admin;
import com.furelise.admin.repository.AdminRepository;
import com.furelise.exception.UnauthorizedException;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminR;
	
	public Admin findByAdminAcc(String adminAcc) {
		return this.adminR.findByAdminAcc(adminAcc);
	}

	public Admin verify(AdminLoginDTO dto) {
		Admin admin = this.findByAdminAcc(dto.getAccount());
		// 判斷是否撈到管理員帳號，或者是已經被停權了，又或者是密碼不同，往外拋 401 的 exception
		if (admin == null || !dto.getPassword().equals(admin.getAdPass())) {
			throw new UnauthorizedException("The account or password is incorrect");
		} else if (admin.getAdStatus() == true) {
			throw new UnauthorizedException("The account is suspended");
		}
		return admin;
	}
	
}
