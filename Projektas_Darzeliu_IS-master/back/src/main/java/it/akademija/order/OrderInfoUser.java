package it.akademija.order;


import java.time.LocalDate;

public class OrderInfoUser {

	private Long id;

	private String childName;

	private String childSurname;

	private LocalDate submitedAt;

	private String kindergartenName;

	private String kindergartenCode;
	
	private boolean status;

	public OrderInfoUser() {

	}

	public OrderInfoUser(Long id, String childName, String childSurname, LocalDate submitedAt,
			String kindergartenName, String kindergartenCode, boolean status) {
		super();
		this.id = id;
		this.childName = childName;
		this.childSurname = childSurname;
		this.submitedAt = submitedAt;
		this.kindergartenName = kindergartenName;
		this.kindergartenCode = kindergartenCode;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildSurname() {
		return childSurname;
	}

	public void setChildSurname(String childSurname) {
		this.childSurname = childSurname;
	}

	public LocalDate getSubmitedAt() {
		return submitedAt;
	}

	public void setSubmitedAt(LocalDate submitedAt) {
		this.submitedAt = submitedAt;
	}

	public String getKindergartenName() {
		return kindergartenName;
	}

	public void setKindergartenName(String kindergartenName) {
		this.kindergartenName = kindergartenName;
	}

	public String getKindergartenAccountNumber() {
		return kindergartenCode;
	}

	public void setKindergartenAccountNumber(String kindergartenAccountNumber) {
		this.kindergartenCode = kindergartenAccountNumber;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
