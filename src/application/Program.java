package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			System.out.print("Room number: ");
			Integer roomNumber = sc.nextInt();
			sc.nextLine();
			
			System.out.print("Check-in date (DD/MM/YYYY): ");
			LocalDate checkIn = LocalDate.parse(sc.nextLine(), formatter);
			
			System.out.print("Check-out date (DD/MM/YYYY): ");
			LocalDate checkOut = LocalDate.parse(sc.nextLine(), formatter);
			
			Reservation r = new Reservation(roomNumber, checkIn, checkOut);
			
			System.out.println("Reservation: " + r);
			
			System.out.println();
			System.out.println("Enter date to update the reservation: ");
			
			System.out.print("Check-in date (DD/MM/YYYY): ");
			checkIn = LocalDate.parse(sc.nextLine(), formatter);
			
			System.out.print("Check-out date (DD/MM/YYYY): ");
			checkOut = LocalDate.parse(sc.nextLine(), formatter);
			
			r.updateDates(checkIn, checkOut);
			
			System.out.println("Reservation: " + r);
		}
		catch(DateTimeParseException dtpe) {
			System.out.println("Error in reservation: Data format not acepted!");
		}
		catch(DomainException de) {
			System.out.println("Error in reservation: " + de.getMessage());
		}
		catch(RuntimeException re) {
			System.out.println("Unexpected error");
		}
		
		sc.close();
	}

}