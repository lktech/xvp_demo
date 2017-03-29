package com.lingke.xvp.demo.exception;

public class DemoException  extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	
	private Throwable t;
	
	public DemoException(String code,String message){
		super(message);
		this.code=code;
		this.message=message;
	}

	public DemoException(String code,String message,Throwable t){
		super(message,t);
		this.code=code;
		this.message=message;
		this.t=t;
	}

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Throwable getT() {
		return t;
	}


	public void setT(Throwable t) {
		this.t = t;
	}
	
	
}
