package it.akademija.serviceProvider;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import it.akademija.serviceGroup.ServiceGroup;


@Entity
public class ServiceProvider {

	@Id
	@Column
	private String code;

	@NotBlank(message = "Pavadinimas privalomas")
	@Column
	private String name;

	@Column
	@NotBlank(message = "Adresas privalomas")
	private String address;

	@NotBlank(message = "Telefono numeris privalomas")
	@Column
	private String phone;

	@NotBlank(message = "El. paštas privalomas!")
	@Column
	private String email;
	
	@ManyToMany
	public Collection<ServiceGroup> serviceGroups=new ArrayList<ServiceGroup>();
	


	public ServiceProvider() {

	}


	public ServiceProvider(
			String code,
			@NotBlank(message = "Pavadinimas privalomas") String name,
			@NotBlank(message = "Adresas privalomas") String address,
			@NotBlank(message = "Telefono numeris privalomas") String phone,
			@NotBlank(message = "El. paštas privalomas!") String email, 
			Collection<ServiceGroup> serviceGroups) {
		super();
		this.code = code;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.serviceGroups = serviceGroups;
	}


	public ServiceProvider(
			String code,
			@NotBlank(message = "Pavadinimas privalomas") String name,
			@NotBlank(message = "Adresas privalomas") String address,
			@NotBlank(message = "Telefono numeris privalomas")String phone,
			@NotBlank(message = "El. paštas privalomas!") String email) {
		super();
		this.code = code;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
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
