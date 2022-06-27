package it.akademija.serviceItem;

import java.util.Collection;

import it.akademija.serviceGroup.ServiceGroup;

public class ServiceItemDTO {

	private Long itemId;

	private String name;

	private String description;
	
	private Collection<ServiceGroup> serviceGroups;


	public ServiceItemDTO(Long itemId, String name, String description) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.description = description;
	}

	public ServiceItemDTO(ServiceItem serviceItem) {
		super();

		this.itemId = serviceItem.getId();
		this.name = serviceItem.getName();
		this.description = serviceItem.getDescription();
		this.serviceGroups = serviceItem.getServiceGroups();
		
	}

	/**
	 * Create ServiceItemDTO from ServiceItem
	 *
	 * @param PrivateKindergarten
	 * @return
	 */

	public static ServiceItemDTO from(ServiceItem serviceItem) {
		return new ServiceItemDTO(serviceItem);
	}

	/**
	 * Convert to ServiceItem
	 *
	 * @return
	 */
	public ServiceItem toServiceItem() {
		return new ServiceItem(itemId, name, description, serviceGroups);
	}

	public Long getItemId() {
		return itemId;
	}
	


	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public Collection<ServiceGroup> getServiceGroups() {
		return serviceGroups;
	}

	public void setServiceGroups(Collection<ServiceGroup> serviceGroups) {
		this.serviceGroups = serviceGroups;
	}


}
