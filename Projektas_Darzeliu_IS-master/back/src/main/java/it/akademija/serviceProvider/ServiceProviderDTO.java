package it.akademija.serviceProvider;

import java.util.ArrayList;
import java.util.Collection;

import it.akademija.serviceGroup.ServiceGroup;

public class ServiceProviderDTO {

	
	private String code;

	private String name;

	private String address;

	private String phone;

	private String email;
	
	public Collection<ServiceGroup> serviceGroups=new ArrayList<ServiceGroup>();
	


	public ServiceProviderDTO(String code, String name, String address,
			String phone, String email, Collection<ServiceGroup> serviceGroups) {
		super();
		this.code = code;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.serviceGroups = serviceGroups;

	}

	public ServiceProviderDTO(ServiceProvider privateKindergarten) {
		super();

		this.code = privateKindergarten.getCode();
		this.name = privateKindergarten.getName();
		this.address = privateKindergarten.getAddress();
		this.phone = privateKindergarten.getPhone();
		this.email = privateKindergarten.getEmail();
		this.serviceGroups = privateKindergarten.getServiceGroups();
	}

	/**
	 * Create PrivateKindergartenDTO from PrivateKindergarten
	 *
	 * @param PrivateKindergarten
	 * @return
	 */

	public static ServiceProviderDTO from(ServiceProvider privateKindergarten) {
		return new ServiceProviderDTO(privateKindergarten);
	}

	/**
	 * Convert to PrivateKindergarten
	 *
	 * @return
	 */
	public ServiceProvider toPrivateKindergarten() {
		return new ServiceProvider(code, name, address, phone,
				email, serviceGroups);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<ServiceGroup> getServiceGroups() {
		return serviceGroups;
	}

	public void setServiceGroups(Collection<ServiceGroup> serviceGroups) {
		this.serviceGroups = serviceGroups;
	}




}
