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
import com.lingke.xvp.demo.controller.response.SuccessResponse;
import com.lingke.xvp.demo.exception.DemoException;
import com.lingke.xvp.demo.exception.UserNoLoginException;

@Component
@Aspect
public class XvpAspect {
	private final Logger logger = LoggerFactory.getLogger(XvpAspect.class);

	@Pointcut("execution(* com.lingke.xvp.demo.controller..*.*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object doAround(ProceedingJoinPoint joinPoint) {
		try {
			String method = joinPoint.getSignature().toShortString();
			logger.info(
					"调用接口开始：{}，请求参数：{}",
					method,
					joinPoint.getArgs().length > 0 ? joinPoint.getArgs()[0]
							.toString() : null);
			Object result = joinPoint.proceed();
			logger.info("调用接口结束：{}，响应参数：{}", method, result == null ? ""
					: result.toString());
			SuccessResponse successResponse = new SuccessResponse();
			successResponse.setCode(XvpConstants.SUCCESS);
			if (result != null) {
				successResponse.setResult(result);
			}
			return successResponse;
		}catch (DemoException e) {
			logger.error("请求处理返回OpenAPI异常， errorcode： {} , errorMessage：{}", e.getMessage(),e.getCode(), e);
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setCode(e.getCode());
			exceptionResponse.setMessage(e.getMessage());
			return exceptionResponse;
		} 
		catch (UserNoLoginException e) {
			logger.error("请求处理返回用户没有登录异常， errorcode： {} , errorMessage：{}", e.getMessage(),e.getCode(), e);
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setCode("user_seller_error");
			exceptionResponse.setMessage("用户没有登录");
			return exceptionResponse;
		} 
		catch (RuntimeException e) {
			logger.error("请求处理返回运行时异常，errorMessage：{}", e.getMessage(), e);
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setCode(XvpConstants.ERROR_CODE01);
			exceptionResponse.setMessage(e.getMessage());
			return exceptionResponse;
		} catch (ApiException e) {
			logger.error("请求处理返回Rop调用异常，errorMessage：{}", e.getMessage(), e);
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setCode(XvpConstants.ERROR_CODE02);
			exceptionResponse.setMessage(e.getMessage());
			return exceptionResponse;
		} catch (Throwable e) {
			logger.error("请求处理返回其他异常，errorMessage：{}", e.getMessage(), e);
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setCode(XvpConstants.ERROR_CODE03);
			exceptionResponse.setMessage(XvpConstants.ERROR_MESSAGE03);
			return exceptionResponse;
		}
	}
}
