package it.akademija.serviceGroup;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@OneToMany(mappedBy = "serviceGroup", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	public Collection<ServiceProvider> books=new ArrayList<ServiceProvider>();


	public ServiceGroup() {

	}

	public ServiceGroup(Long id, @NotBlank(message = "Pavadinimas privalomas") String name) {
		super();
		this.id = id;
		this.name = name;

	}

	public ServiceGroup(Long id, @NotBlank(message = "Pavadinimas privalomas") String name,
			Collection<ServiceProvider> books) {
		super();
		this.id = id;
		this.name = name;
		this.books = books;
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

	public Collection<ServiceProvider> getBooks() {
		return books;
	}

	public void setBooks(Collection<ServiceProvider> books) {
		this.books = books;
	}



}
