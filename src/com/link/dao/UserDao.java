package com.link.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.link.domain.UserModel;

/**
 * 用户对象Dao
 * 
 * @author Ruikun
 * 
 */
@Repository("userDao")
public class UserDao extends BaseJdbcSupport {

	private SimpleJdbcInsert insertActor = null;
	private final String SQL_SELECT_USER_BY_DEVICEID = "SELECT * FROM link_user WHERE devicd_id=?";

	@PostConstruct
	public void init() {
		insertActor = new SimpleJdbcInsert(getJdbcTemplate().getDataSource())
				.withTableName("link_user").usingGeneratedKeyColumns("id");
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return super.linkJdbcTemplate;

	}

	public boolean add(UserModel user) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
		Number num = insertActor.executeAndReturnKey(parameters);
		if (num != null && num.intValue() > 0) {
			user.setId(num.intValue());
			return true;
		}
		return false;
	}

	public UserModel findByDeviceId(String deviceId) {
		List<UserModel> userList = getJdbcTemplate().query(
				SQL_SELECT_USER_BY_DEVICEID, new Object[] { deviceId },
				new UserMapper());
		if (userList != null) {
			return userList.get(0);
		}
		return null;
	}

	private class UserMapper implements RowMapper<UserModel> {

		@Override
		public UserModel mapRow(ResultSet rs, int arg1) throws SQLException {
			UserModel user = new UserModel();
			user.setId(rs.getInt("id"));
			user.setCode(rs.getString("code"));
			user.setDeviceId(rs.getString("device_id"));
			user.setNick(rs.getString("nick"));
			user.setImage(rs.getString("image"));
			user.setLatitude(rs.getDouble("latitude"));
			user.setLongitude(rs.getDouble("longitude"));
			user.setCreateTime(rs.getTimestamp("create_time"));
			user.setLastActiveTime(rs.getTimestamp("last_active_time"));
			return user;
		}

	}

}
