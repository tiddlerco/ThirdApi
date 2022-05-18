package com.coderbuff.third1simple.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.coderbuff.third1simple.consont.BizCodeEnume;
import com.coderbuff.third1simple.consont.Result;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: SecKillSentinelConfig</p>
 * Description：配置请求被限制以后的处理器
 */
@Configuration
public class SentinelConfig implements BlockExceptionHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
		// 降级业务处理
		Result error = Result.error(BizCodeEnume.TO_MANY_REQUEST.getCode(), BizCodeEnume.TO_MANY_REQUEST.getMsg());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(JSON.toJSONString(error));
	}
}
