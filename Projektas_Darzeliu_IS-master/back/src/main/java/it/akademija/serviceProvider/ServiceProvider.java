package it.akademija.serviceProvider;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import it.akademija.order.OrderEntity;
import it.akademija.serviceGroup.ServiceGroup;
import it.akademija.user.User;
//BOOK
@Entity
public class ServiceProvider {

	//isbn
	@Id
	@Column
	private String code;

	@NotBlank(message = "Privalomas")
	@Column
	private String name;

	@Column
	@NotBlank(message = "Privalomas")
	private String description;

	@NotBlank (message = "Privalomas")
	@Column
	private String pages;

	@NotBlank(message = "Privalomas")
	@Column
	private String image;
	
	//kategorija
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH })
	@JoinColumn(name = "id")
	public ServiceGroup serviceGroup;
	//rezervacija
	@OneToOne
	public OrderEntity Order;
	//

	@ManyToMany
	public Collection<User> usersLike =  new ArrayList<User>();


	public ServiceProvider() {

	}


	public ServiceProvider(
			String code,
			@NotBlank(message = "Privalomas") String name,
			@NotBlank(message = "Privalomas") String address,
			@NotBlank(message = "Privalomas")String phone,
			@NotBlank(message = "Privalomas") String email) {
		super();
		this.code = code;
		this.name = name;
		this.description = address;
		this.pages = phone;
		this.image = email;
	}


	public ServiceProvider(String code, @NotBlank(message = "Privalomas") String name,
		@NotBlank(message = "Privalomas") String description, @NotBlank(message = "Privalomas") String pages,
		@NotBlank(message = "Privalomas") String image, ServiceGroup serviceGroup, OrderEntity order) {
	super();
	this.code = code;
	this.name = name;
	this.description = description;
	this.pages = pages;
	this.image = image;
	this.serviceGroup = serviceGroup;
	Order = order;

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
		return description;
	}


	public void setAddress(String address) {
		this.description = address;
	}


	public String getPhone() {
		return pages;
	}


	public void setPhone(String phone) {
		this.pages = phone;
	}


	public String getEmail() {
		return image;
	}


	public void setEmail(String email) {
		this.image = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ServiceGroup getServiceGroup() {
		return serviceGroup;
	}

	public void setServiceGroup(ServiceGroup serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	public OrderEntity getOrder() {
		return Order;
	}

	public void setOrder(OrderEntity order) {
		Order = order;
	}







	

}
