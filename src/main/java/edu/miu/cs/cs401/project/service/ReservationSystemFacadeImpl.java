package edu.miu.cs.cs401.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import edu.miu.cs.cs401.project.domain.*;
import edu.miu.cs.cs401.project.helpers.StorageHandler;

public class ReservationSystemFacadeImpl implements ReservationSystemFacade {

	@Override
	public List<Airport> findAllAirports() {
		return StorageHandler.airports;
	}

	@Override
	public Airport findAirportByAirportCode(String airportCode) {
		for (Airport airport: StorageHandler.airports)
			if (airport.getCode().equals(airportCode))
				return airport;
		return null;
	}

	@Override
	public List<Airport> findAirportsByCity(String city) {
		List<Airport> airports = new ArrayList<>();
		for (Airport airport: StorageHandler.airports)
			if (airport.getAddress().getCity().equals(city))
				airports.add(airport);
		return airports;
	}

	@Override
	public List<Airline> findAirlinesByAirportCode(String airportCode) {
		Airport departureAirport = findAirportByAirportCode(airportCode);

		if (departureAirport == null) return new ArrayList<>();

		// perform the query
		List<FlightNumber> depFlights = departureAirport.getDepartureFlights();
		List<Airline> airlines = new ArrayList<>();
		for (FlightNumber depFlight: depFlights) {
			Airline depFlightAirline = depFlight.getAirlineOwn();
			if (!airlines.contains(depFlightAirline)) {
				airlines.add(depFlightAirline);
			}

		}

		return airlines;
	}

	@Override
	public List<Flight> findFlightsFromTo(String departure, String arrival) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findReservationsByPassengerId(Integer passengerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Passenger> findPassengersByAgentCode(String agentCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation createReservation(Passenger passenger, List<Flight> flights) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation createReservation(Agent agent, Passenger passenger, List<Flight> flights) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmReservation(String reservationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelReservation(String reservationCode) {
		// TODO Auto-generated method stub
		
	}

}
