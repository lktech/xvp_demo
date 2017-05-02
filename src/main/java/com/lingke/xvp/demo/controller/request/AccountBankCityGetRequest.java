package com.lingke.xvp.demo.controller.request;

public class AccountBankCityGetRequest implements XvpRequest {
	private String citycode;

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	@Override
	public String toString() {
		return "AccountBankCityGetRequest [citycode=" + citycode + "]";
	}
	
}
