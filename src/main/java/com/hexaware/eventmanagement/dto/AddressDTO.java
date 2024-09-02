package com.hexaware.eventmanagement.dto;


import com.hexaware.eventmanagement.entity.Employee;

public class AddressDTO {
	
	
	private long addressId;
	private String city;
	private String addressLine;
	private String state;
	private int pincode;

	private Employee employee;


	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "AddressDTO{" +
				"addressId=" + addressId +
				", city='" + city + '\'' +
				", addressLine='" + addressLine + '\'' +
				", state='" + state + '\'' +
				", pincode=" + pincode +
				", employee=" + employee +
				'}';
	}

	public AddressDTO(long addressId, String city, String addressLine, String state, int pincode, Employee employee) {
		this.addressId = addressId;
		this.city = city;
		this.addressLine = addressLine;
		this.state = state;
		this.pincode = pincode;
		this.employee = employee;
	}

	public AddressDTO() {
	}
}
