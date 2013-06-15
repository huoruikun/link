package com.link.domain;

import java.util.Date;

/**
 * 专栏对象
 * 
 * @author Ruikun
 * 
 */
public class ColumnModel {
	int id;
	String name;
	String summary;
	double latitude;
	double longitude;
	double altitude;
	double laSpan;
	double loSpan;
	double alSpan;
	Date createTime;
	int creatorId;
	String coverUrl;
	int status;
	long gossipCount;
	long visitorCount;
	String geoHash;
}
