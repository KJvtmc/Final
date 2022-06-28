package it.akademija.order;


import java.time.LocalDate;

public class OrderInfoUser {

	private Long id;

	private LocalDate submitedAt;

	private String bookName;

	private String bookCode;
	
	private boolean status;

	public OrderInfoUser() {

	}

	public OrderInfoUser(Long id, LocalDate submitedAt,
			String kindergartenName, String kindergartenCode, boolean status) {
		super();
		this.id = id;
		this.submitedAt = submitedAt;
		this.bookName = kindergartenName;
		this.bookCode = kindergartenCode;
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

	public void setSubmitedAt(LocalDate submitedAt) {
		this.submitedAt = submitedAt;
	}

	public String getKindergartenName() {
		return bookName;
	}

	public void setKindergartenName(String kindergartenName) {
		this.bookName = kindergartenName;
	}

	public String getKindergartenAccountNumber() {
		return bookCode;
	}

	public void setKindergartenAccountNumber(String kindergartenAccountNumber) {
		this.bookCode = kindergartenAccountNumber;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
