package it.akademija.serviceGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.akademija.serviceItem.ServiceItem;
import it.akademija.serviceProvider.ServiceProvider;

public class ServiceGroupDTO {

	private Long id;

	private String name;

	
	public Collection<ServiceProvider> books=new ArrayList<ServiceProvider>();


	public ServiceGroupDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;

	}

	public ServiceGroupDTO(ServiceGroup serviceGroup) {
		super();

		this.id = serviceGroup.getId();
		this.name = serviceGroup.getName();
		this.books = serviceGroup.getBooks();

	}

	/**
	 * Create ServiceGroupDTO from ServiceGroup
	 *
	 * @param PrivateKindergarten
	 * @return
	 */

	public static ServiceGroupDTO from(ServiceGroup serviceGroup) {
		return new ServiceGroupDTO(serviceGroup);
	}

	/**
	 * Convert to ServiceGroup
	 *
	 * @return
	 */
	public ServiceGroup toServiceGroup() {
		return new ServiceGroup(id, name, books);
	}

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ServiceProvider> getBooks() {
		return books;
	}

	public void setBooks(Collection<ServiceProvider> books) {
		this.books = books;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
