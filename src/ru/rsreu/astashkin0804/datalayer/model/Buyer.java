package ru.rsreu.astashkin0804.datalayer.model;

public final class Buyer {
	private int id;
	private String telephoneNumber;
	private String contactPerson;
	private String address;

	public Buyer(int id, String telephoneNumber, String contactPerson, String address) {
		this.id = id;
		this.telephoneNumber = telephoneNumber;
		this.contactPerson = contactPerson;
		this.address = address;
	}
	
	public int getId() {
		return this.id;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public String getAddress() {
		return this.address;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d, telephone: %s, person: %s, address: %s", this.id, this.telephoneNumber, this.contactPerson, this.address);
	}

}
