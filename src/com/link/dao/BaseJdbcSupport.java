package com.link.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseJdbcSupport {
	@Autowired
	@Qualifier("linkJdbcTemplate")
	protected JdbcTemplate linkJdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return linkJdbcTemplate;
	}

}
