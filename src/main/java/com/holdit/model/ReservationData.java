package com.holdit.model;

public class ReservationData {
    
    private int partySize;
    private String reservationDate;
    private String reservationSlot;
    private String partyName;
    private String contactPhone;
    private String contactEmail;

	public ReservationData(int partySize, String reservationDate, String reservationSlot, String partyName,
			String contactPhone, String contactEmail) {
		this.partySize = partySize;
		this.reservationDate = reservationDate;
		this.reservationSlot = reservationSlot;
		this.partyName = partyName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
	}

	public int getPartySize() {
		return partySize;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public String getReservationSlot() {
		return reservationSlot;
	}

	public String getPartyName() {
		return partyName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}
}
