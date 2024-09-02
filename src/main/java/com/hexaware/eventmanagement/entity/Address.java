package com.hexaware.eventmanagement.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_generator")
	@SequenceGenerator(name = "address_seq_generator", sequenceName = "address_seq", allocationSize = 1, initialValue =130000)
	private long addressId;

	@NotEmpty(message = "City cannot be empty")
	@Size(max = 50, message = "City cannot exceed 50 characters")
	private String city;

	@NotEmpty(message = "Address line cannot be empty")
	@Size(max = 255, message = "Address line cannot exceed 255 characters")
	private String addressLine;

	@NotEmpty(message = "State cannot be empty")
	@Size(max = 50, message = "State cannot exceed 50 characters")
	private String state;

	@Positive(message = "Pincode must be a positive number")
	@Digits(integer = 6, fraction = 0, message = "Invalid pincode format")
	private int pincode;


	@JsonBackReference
	@OneToOne(mappedBy="address")
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
		return "Address{" +
				"addressId=" + addressId +
				", city='" + city + '\'' +
				", addressLine='" + addressLine + '\'' +
				", state='" + state + '\'' +
				", pincode=" + pincode +
				", employee=" + employee +
				'}';
	}

	public Address(long addressId, String city, String addressLine, String state, int pincode, Employee employee) {
		this.addressId = addressId;
		this.city = city;
		this.addressLine = addressLine;
		this.state = state;
		this.pincode = pincode;
		this.employee = employee;
	}

	public Address() {
	}
}
