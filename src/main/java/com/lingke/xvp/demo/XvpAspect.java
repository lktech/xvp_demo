package com.lingke.xvp.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.Rop.api.ApiException;
import com.lingke.xvp.demo.controller.response.ExceptionResponse;

@Component
@Aspect
public class XvpAspect {
	private final Logger logger = LoggerFactory.getLogger(XvpAspect.class);

	private final String ERROR_CODE01 = "xvp_error_001";
	private final String ERROR_CODE02 = "xvp_error_002";
	private final String ERROR_CODE03 = "xvp_error_003";
	private final String ERROR_MESSAGE03 = "请求处理发生未知失败";

	@Pointcut("execution(* cn.rongcapital.xvp.openapi.client.controller..*.*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object doAround(ProceedingJoinPoint joinPoint) {
		try {
			String method = joinPoint.getSignature().toShortString();
			logger.info("调用接口开始：{}，请求参数：{}", method,
					joinPoint.getArgs()[0].toString());
			Object result = joinPoint.proceed();
			logger.info("调用接口结束：{}，响应参数：{}", method, result.toString());
			return result;
		} catch (RuntimeException e) {
			logger.error("请求处理返回运行时异常，errorMessage：{}", e.getMessage());
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setError_code(ERROR_CODE01);
			exceptionResponse.setError_msg(e.getMessage());
			return exceptionResponse;
		} catch (ApiException e) {
			logger.error("请求处理返回Rop调用异常，errorMessage：{}", e.getMessage());
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setError_code(ERROR_CODE02);
			exceptionResponse.setError_msg(e.getMessage());
			return exceptionResponse;
		} catch (Throwable e) {
			logger.error("请求处理返回其他异常，errorMessage：{}", e.getMessage(), e);
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setError_code(ERROR_CODE03);
			exceptionResponse.setError_msg(ERROR_MESSAGE03);
			return exceptionResponse;
		}
	}
}
