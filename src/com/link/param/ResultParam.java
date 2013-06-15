package com.link.param;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class ResultParam<V> {

	/* json返回的状态 */
	private int code;
	/* 原因描述 */
	private String reason;
	/* 如果有对象，则加上对象信息 */
	private V v;
	/* 扩展信息 */
	private Map<String, Object> exts;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@JsonProperty("data")
	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

	public Map<String, Object> getExts() {
		return exts;
	}

	public void setExts(Map<String, Object> exts) {
		this.exts = exts;
	}

	public void putExt(String key, Object value) {
		if (this.exts == null) {
			this.exts = new HashMap<String, Object>();
		}
		this.exts.put(key, value);
	}
}
