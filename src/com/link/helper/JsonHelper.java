package com.link.helper;

import java.io.StringWriter;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.link.domain.UserModel;
import com.link.param.ResultParam;

public class JsonHelper {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static <T> String toJson(T t) {
		try {
			StringWriter writer = new StringWriter();
			JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
					.createJsonGenerator(writer);
			jsonGenerator.writeObject(t);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		UserModel user = new UserModel();
		user.setDeviceId("121211");
		user.setId(11111);
		user.setCreateTime(new Date());
		ResultParam<UserModel> result = new ResultParam<UserModel>();
		result.setV(user);
		result.putExt("next", 2);
		String json = toJson(result);
		System.out.println(json);
	}
}
