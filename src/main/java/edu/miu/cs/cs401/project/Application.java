package edu.miu.cs.cs401.project;

import edu.miu.cs.cs401.project.domain.Airline;
import edu.miu.cs.cs401.project.domain.Airport;
import edu.miu.cs.cs401.project.domain.Flight;
import edu.miu.cs.cs401.project.domain.FlightNumber;
import edu.miu.cs.cs401.project.helpers.StorageHandler;
import edu.miu.cs.cs401.project.service.ReservationSystemFacade;
import edu.miu.cs.cs401.project.service.ReservationSystemFacadeImpl;

import java.awt.geom.FlatteningPathIterator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application {

	public static void passengerFlow(String name) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome " +name+ " to Airline Reservation System");

		// lets first add some airports.
		StorageHandler.createRandomAirports(10);

		// and create the object for reservation facade implementation
		ReservationSystemFacade facade = new ReservationSystemFacadeImpl();

		// List out use case for user
		System.out.println("This is some action you can do: ");
		System.out.println("1. List all available Airports" +
				"\n2. Search Airport by CODE" +
				"\n3. Search Airport by CITY NAME" +
				"\n4. Some action");

		String action = scanner.nextLine();
		if(action.equals("1")) {
			// here is the list of them
			List<Airport> airports = facade.findAllAirports();
			System.out.println("Here is the list of all airports:");
			for (Airport airport : airports) {
				System.out.println("=================");
				System.out.println("Name: " + airport.getName() +
						"\nCode: " + airport.getCode() +
						"\nAddress: " + airport.getAddress().getStreet() +
						", " + airport.getAddress().getCity() +
						", " + airport.getAddress().getState() +
						", " + airport.getAddress().getZip());
			}
			System.out.println();
		} else if(action.equals("2")) {
			// here is the list of all airlines
			System.out.print("Please enter the code of the airport that you seek: ");
			Airport searchedAirport = facade.findAirportByAirportCode(scanner.nextLine());

			// print out searched airport
			System.out.println("=================");
			System.out.println("Name: " + searchedAirport.getName() +
					"\nCode: " + searchedAirport.getCode() +
					"\nAddress: " + searchedAirport.getAddress().getStreet() +
					", " + searchedAirport.getAddress().getCity() +
					", " + searchedAirport.getAddress().getState() +
					", " + searchedAirport.getAddress().getZip());
			System.out.println();
		} else if(action.equals("3")) {
			// here is the list of all airports in given city
			System.out.print("Please enter the city: ");
			List<Airport> airportsInCity = facade.findAirportsByCity(scanner.nextLine());
			if (airportsInCity.size() == 0)
				System.out.println("No airports in that city :(");
			for (Airport airport : airportsInCity) {
				System.out.println("=================");
				System.out.println("Name: " + airport.getName() +
						"\nCode: " + airport.getCode() +
						"\nAddress: " + airport.getAddress().getStreet() +
						", " + airport.getAddress().getCity() +
						", " + airport.getAddress().getState() +
						", " + airport.getAddress().getZip());
			}
			System.out.println();
		} else if(action.equals("4")) {

			// lets add some flight to the airport that's asked
			int i = 0;
			while (i++ < 20) {
				FlightNumber flightNumber = new FlightNumber(
						i,
						350,
						StorageHandler.getRandomAirline(),
						StorageHandler.airports.get(i % StorageHandler.airports.size()),
						StorageHandler.airports.get((i + 1) % StorageHandler.airports.size()),
						new Date(),
						new Date()
				);
				StorageHandler.flightNumbers.add(flightNumber);
			}

			System.out.println("Creating new flight numbers...");
			for (FlightNumber flightNumber : StorageHandler.flightNumbers)
				System.out.println(flightNumber);
			System.out.println();

			System.out.print("Please enter the airport code: ");
			List<Airline> airlinesByAirportCode = facade.findAirlinesByAirportCode(scanner.nextLine());
			for (Airline airline : airlinesByAirportCode)
				System.out.println(airline);
			System.out.println();
		} else if(action.equals("5")) {
			System.out.print("Please enter name of departure airport: ");
			String departure = scanner.nextLine();
			System.out.print("Please enter name of arrival airport: ");
			String arrival = scanner.nextLine();

			List<Flight> flightsFromTo = facade.findFlightsFromTo(departure, arrival);
			for (Flight flight : flightsFromTo) {
				System.out.println("Flight name: " + flight.getFlightNumber() +
						"Date: " + flight.getFlightDate() +
						"Passenger: " + flight.getPassengers() +
						"Drive by: " + flight.getPilots());
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Who are you???");
		System.out.println("1. Admin\n2. Passenger\n3. Agent");
		String role = scanner.nextLine();
		System.out.println("Can i know your name ?");
		String name = scanner.nextLine();
		if(role.equals("1")){
			//TODO admin workflow

		} else if( role.equals("2")){
			passengerFlow(name);
			//TODO Passenger workflow
		} else if(role.equals("3")){
			//TODO Agent workflow

		}
	}

}
