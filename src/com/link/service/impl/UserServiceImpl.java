package com.link.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.link.dao.UserDao;
import com.link.domain.UserModel;
import com.link.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserModel createUser(UserModel user) {
		if (user != null && user.getDeviceId() != null) {
			// code需要生成
			// user.setCode(code);
			user.setCreateTime(new Date());
			user.setLastActiveTime(user.getCreateTime());
			// user.setNick(nick);
			if (userDao.add(user)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public UserModel updateUser(UserModel user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel findUserByDeviceId(String deviceId) {
		UserModel user = userDao.findByDeviceId(deviceId);
		return user;
	}

}
