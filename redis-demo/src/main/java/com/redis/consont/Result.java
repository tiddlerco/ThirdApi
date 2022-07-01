/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.redis.consont;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * todo 学习泛型类和泛型方法的使用 TypeReference的使用
 * TypeReference
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Result extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param key 获取指定key的名字
	 * @param typeReference
	 * @param <T>
	 * @return
	 */
	public <T> T getData(String key,TypeReference<T> typeReference){
		// get("data") 默认是map类型 所以再由map转成string再转json
		Object data = get(key);
		return JSON.parseObject(JSON.toJSONString(data),typeReference);
	}





	//逆转，返回值是泛型
	public <T> T getData(TypeReference<T> typeReference) {
		Object data = get("data");
		//把data转成json
		String s = JSON.toJSONString(data);
		//json转成对应的泛型
		T t = JSON.parseObject(s, typeReference);
		return t;
	}

	public Result setData(Object data) {
		put("data", data);
		return this;
	}





	public Result() {
		put("code", 0);
		put("msg", "success");
	}


	public static Result error(int code, String msg) {
		Result result = new Result();
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}

	public static Result ok(String msg) {
		Result result = new Result();
		result.put("msg", msg);
		return result;
	}

	public static Result ok(Map<String, Object> map) {
		Result result = new Result();
		result.putAll(map);
		return result;
	}

	public static Result ok() {
		return new Result();
	}

	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	public  Integer getCode() {

		return (Integer) this.get("code");
	}

}
