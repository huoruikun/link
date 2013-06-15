package com.link.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.link.domain.UserModel;
import com.link.service.IUserService;
import com.link.utils.RenderUtils;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	/**
	 * 创建用户信息
	 * 
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/create")
	public void create(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, UserModel user) {
		UserModel createdUser = userService.createUser(user);
		if (createdUser != null) {
			try {
				PrintWriter print = response.getWriter();
				print.write("succ, id=" + user.getId());
				response.flushBuffer();
			} catch (IOException e) {
				logger.info("error", e);
			}
		}
	}

	/**
	 * test
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "test")
	public void news(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, UserModel user) {
		String json = "{\"data\":123}";
		RenderUtils.renderJson(response, json);
	}
}
