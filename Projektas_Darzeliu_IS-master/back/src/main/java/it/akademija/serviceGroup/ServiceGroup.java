package it.akademija.serviceGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import it.akademija.serviceItem.ServiceItem;
import it.akademija.serviceProvider.ServiceProvider;

@Entity
public class ServiceGroup {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Pavadinimas privalomas")
	@Column
	private String name;

	@Column
	@NotBlank(message = "Aprašymas privalomas")
	private String description;
	
	@ManyToMany
	private Collection<ServiceProvider> serviceProviders=new ArrayList<ServiceProvider>();
	
	@ManyToMany
	public Collection<ServiceItem> serviceItems=new ArrayList<ServiceItem>();


	public ServiceGroup() {

	}

	public ServiceGroup(Long id, @NotBlank(message = "Pavadinimas privalomas") String name,
			@NotBlank(message = "Aprašymas privalomas") String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;

	}

	public ServiceGroup(Long id, @NotBlank(message = "Pavadinimas privalomas") String name,
			@NotBlank(message = "Aprašymas privalomas") String description, Collection<ServiceProvider> serviceProviders,
			Collection<ServiceItem> serviceItems) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.serviceProviders = serviceProviders;
		this.serviceItems = serviceItems;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public Collection<ServiceProvider> getServiceProviders() {
		return serviceProviders;
	}


	public void setServiceProviders(Collection<ServiceProvider> serviceProviders) {
		this.serviceProviders = serviceProviders;
	}

	public Collection<ServiceItem> getServiceItems() {
		return serviceItems;
	}

	public void setServiceItems(Collection<ServiceItem> serviceItems) {
		this.serviceItems = serviceItems;
	}




}
