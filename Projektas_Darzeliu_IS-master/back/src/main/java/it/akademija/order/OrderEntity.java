package it.akademija.order;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import it.akademija.serviceProvider.ServiceProvider;
import it.akademija.user.User;

@Entity
public class OrderEntity {

	@Id
	@Column(name = "application_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "date_of_submition", columnDefinition = "DATE")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate submitedAt;

	@NotNull(message = "Knyga privaloma!")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "serviceProvider", referencedColumnName="code")
	private ServiceProvider serviceProvider;

	@NotNull(message = "Klientas privalomas!")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User mainGuardian;
	
	@Column(name = "status")
	private boolean status;

	public OrderEntity() {

	}
	
	public OrderEntity(Long id, LocalDate submitedAt,
			@NotNull(message = "Knyga privaloma!") ServiceProvider serviceProvider,
			@NotNull(message = "Klientas privalomas!") User mainGuardian, boolean status) {
		super();
		this.id = id;
		this.submitedAt = submitedAt;
		this.serviceProvider = serviceProvider;
		this.mainGuardian = mainGuardian;
		this.status = status;
	}


	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getSubmitedAt() {
		return submitedAt;
	}

	public void setSubmitedAt() {
		this.submitedAt = LocalDate.now();
	}


	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider ServiceProvider) {
		this.serviceProvider = ServiceProvider;
	}

	public User getMainGuardian() {
		return mainGuardian;
	}

	public void setMainGuardian(User mainGuardian) {
		this.mainGuardian = mainGuardian;
	}

}
