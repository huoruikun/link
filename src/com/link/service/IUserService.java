package com.link.service;

import com.link.domain.UserModel;

/**
 * 用户service接口
 * @author Ruikun
 *
 */
public interface IUserService {

	/**
	 * 创建一个用户，创建完成后就会有id
	 * @param user
	 * @return
	 */
	public UserModel createUser(UserModel user);
	
	/**
	 * 更新一个用户信息，where条件为code
	 * @param user
	 * @return
	 */
	public UserModel updateUser(UserModel user);
	
	/**
	 * 通过设备Id查询用户信息
	 * @param deviceId
	 * @return
	 */
	public UserModel findUserByDeviceId(String deviceId);
}
