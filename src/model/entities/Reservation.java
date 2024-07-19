package model.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	Instant now = Instant.now();
	LocalDate nowLocalDate = now.atZone(ZoneId.systemDefault()).toLocalDate(); // transforma o instant para um local date
	

	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) throws DomainException {
		if (checkIn.isAfter(checkOut)) {
			throw new DomainException("Check-out date must be after check-in date");
		} else if (checkIn.isBefore(nowLocalDate)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public long duration() {
		return ChronoUnit.DAYS.between(checkIn, checkOut);
	}

	public void updateDates(LocalDate checkIn, LocalDate checkOut) throws DomainException{
		
		if (checkIn.isAfter(checkOut)) {
			throw new DomainException("Check-out date must be after check-in date");
		} else if (checkIn.isBefore(nowLocalDate)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;	
	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", Check-in: " + checkIn.format(formatter) + ", Check-out: "
				+ checkOut.format(formatter) + ", " + duration() + " nights";
	}

}