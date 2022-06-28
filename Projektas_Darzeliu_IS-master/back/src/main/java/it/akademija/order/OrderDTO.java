package it.akademija.order;


import java.time.LocalDate;

import it.akademija.serviceProvider.ServiceProviderDTO;
import it.akademija.user.UserDTO;
import it.akademija.user.UserInfo;

public class OrderDTO {
	private Long id;

	private LocalDate submitedAt;
	
	private boolean status;

	private ServiceProviderDTO book;

	private UserInfo mainGuardian;

	public OrderDTO() {

	}

	public OrderDTO(LocalDate submitedAt, ServiceProviderDTO privateKindergarten,
			UserInfo mainGuardian, boolean status ) {
		super();
		this.submitedAt = submitedAt;
		this.book = privateKindergarten;
		this.mainGuardian = mainGuardian;
		this.status = status;
	}



	public OrderDTO(OrderEntity order) {
		super();
		this.id = order.getId();
		this.submitedAt = order.getSubmitedAt();
		this.book = book.from(order.getServiceProvider());
		this.mainGuardian = mainGuardian.from(order.getMainGuardian());
		this.status = order.isStatus();
	}

	/**
	 * Create CompensationApplicationDTO from CompensationApplication
	 *
	 * @param CompensationApplication
	 * @return
	 */

	public static OrderDTO from(OrderEntity compensationApplication) {
		return new OrderDTO(compensationApplication);
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServiceProviderDTO getBook() {
		return book;
	}

	public void setBook(ServiceProviderDTO book) {
		this.book = book;
	}

	public ServiceProviderDTO getPrivateKindergarten() {
		return book;
	}

	public void setPrivateKindergarten(ServiceProviderDTO privateKindergarten) {
		this.book = privateKindergarten;
	}

	public UserInfo getMainGuardian() {
		return mainGuardian;
	}

	public void setMainGuardian(UserInfo mainGuardian) {
		this.mainGuardian = mainGuardian;
	}

	public LocalDate getSubmitedAt() {
		return submitedAt;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setSubmitedAt(LocalDate submitedAt) {
		this.submitedAt = submitedAt;
	}


}
