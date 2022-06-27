package it.akademija.serviceGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.akademija.serviceItem.ServiceItem;
import it.akademija.serviceProvider.ServiceProvider;

public class ServiceGroupDTO {

	private Long id;

	private String name;

	private String description;
	
	private Collection<ServiceProvider> serviceProviders=new ArrayList<ServiceProvider>();

	public Collection<ServiceItem> serviceItems=new ArrayList<ServiceItem>();


	public ServiceGroupDTO(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;

	}

	public ServiceGroupDTO(ServiceGroup serviceGroup) {
		super();

		this.id = serviceGroup.getId();
		this.name = serviceGroup.getName();
		this.description = serviceGroup.getDescription();
		this.serviceProviders = serviceGroup.getServiceProviders();
		this.serviceItems = serviceGroup.getServiceItems();
		
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
		return new ServiceGroup(id, name, description, serviceProviders, serviceItems);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<ServiceProvider> getServiceProvider() {
		return serviceProviders;
	}

	public void setServiceProvider(Collection<ServiceProvider> serviceProviders) {
		this.serviceProviders = serviceProviders;
	}

	public Collection<ServiceItem> getServiceItems() {
		return serviceItems;
	}

	public void setServiceItems(Collection<ServiceItem> serviceItems) {
		this.serviceItems = serviceItems;
	}


}
