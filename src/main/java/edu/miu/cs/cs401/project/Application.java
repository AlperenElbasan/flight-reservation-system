package edu.miu.cs.cs401.project;

import edu.miu.cs.cs401.project.domain.Airline;
import edu.miu.cs.cs401.project.domain.Airport;
import edu.miu.cs.cs401.project.domain.FlightNumber;
import edu.miu.cs.cs401.project.helpers.StorageHandler;
import edu.miu.cs.cs401.project.service.ReservationSystemFacade;
import edu.miu.cs.cs401.project.service.ReservationSystemFacadeImpl;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		System.out.println("Airline Reservation System");
		Scanner scanner = new Scanner(System.in);

		// lets first add some airports.
		StorageHandler.createRandomAirports(10);

		// and create the object for reservation facade implementation
		ReservationSystemFacade facade = new ReservationSystemFacadeImpl();

		// here is the list of them
		List<Airport> airports = facade.findAllAirports();
		System.out.println("Here is the list of all airports:");
		for (Airport airport: airports)
			System.out.println(airport);
		System.out.println();


		// here is the list of all airlines
		System.out.print("Please enter the code of the airport that you seek: ");
		Airport searchedAirport = facade.findAirportByAirportCode(scanner.nextLine());
		System.out.println(searchedAirport + "\n");

		// here is the list of all airports in given city
		System.out.print("Please enter the city: ");
		List<Airport> airportsInCity = facade.findAirportsByCity(scanner.nextLine());
		if (airportsInCity.size() == 0)
			System.out.println("No airports in that city :(");
		for (Airport airport: airportsInCity)
			System.out.println(airport);
		System.out.println();

		// lets add some flight to the airport that's asked
		int i = 0;
		while (i++ < 20) {
			FlightNumber flightNumber = new FlightNumber(
					i,
					350,
					StorageHandler.getRandomAirline(),
					StorageHandler.airports.get(i % StorageHandler.airports.size()) ,
					StorageHandler.airports.get((i + 1) % StorageHandler.airports.size()),
					new Date(),
					new Date()
			);
			StorageHandler.flightNumbers.add(flightNumber);
		}

		System.out.println("Creating new flight numbers...");
		for (FlightNumber flightNumber: StorageHandler.flightNumbers)
			System.out.println(flightNumber);
		System.out.println();

		System.out.print("Please enter the airport code: ");
		List<Airline> airlinesByAirportCode = facade.findAirlinesByAirportCode(scanner.nextLine());
		for (Airline airline: airlinesByAirportCode)
			System.out.println(airline);
		System.out.println();
	}

}
