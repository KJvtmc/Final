package it.akademija.serviceProvider;

import it.akademija.order.OrderEntity;
import it.akademija.serviceGroup.ServiceGroup;

public class ServiceProviderDTO {

	
	//isbn

		private String code;

		private String name;

		private String description;

		private String pages;

		private String image;
//kategorija
		public ServiceGroup serviceGroup;

		public OrderEntity order;

	




	public ServiceProviderDTO(String code, String name, String description, String pages, String image,
		ServiceGroup serviceGroup, OrderEntity order) {
	super();
	this.code = code;
	this.name = name;
	this.description = description;
	this.pages = pages;
	this.image = image;
	this.serviceGroup = serviceGroup;
	this.order = order;

}

	public ServiceProviderDTO(ServiceProvider book) {
		super();

		this.code = book.getCode();
		this.name = book.getName();
		this.description = book.getDescription();
		this.pages = book.getPages();
		this.image = book.getImage();
		this.serviceGroup = book.getServiceGroup();
		this.order = book.getOrder();

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
		return new ServiceProvider(code, name, description, pages,
				image,serviceGroup, order );
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
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}


	

}
