package edu.miu.cs.cs401.project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.miu.cs.cs401.project.domain.Airline;
import edu.miu.cs.cs401.project.domain.Airport;
import edu.miu.cs.cs401.project.domain.FlightNumber;
import edu.miu.cs.cs401.project.helpers.StorageHandler;
import edu.miu.cs.cs401.project.service.ReservationSystemFacade;
import edu.miu.cs.cs401.project.service.ReservationSystemFacadeImpl;

class ReservationSystemFacadeImplTest {

	ReservationSystemFacade facade = new ReservationSystemFacadeImpl(); 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void findAllAirportsTest() {
		StorageHandler.emptyAirports();
		List<Airport> airports = facade.findAllAirports();
		assertNotNull(airports);
		assertTrue(airports.isEmpty());
		
		StorageHandler.createRandomAirports(10);
		airports = facade.findAllAirports();
		assertNotNull(airports);
		assertTrue(airports.size() == 10);
	}
	
	@Test
	void findAirportByAirportCodeTest() {
		StorageHandler.emptyAirports();
		StorageHandler.createRandomAirports(10);
		
		Airport airport = facade.findAirportByAirportCode(StorageHandler.airports.get(0).getCode());
		assertNotNull(airport);
	}
	
	@Test
	void findAirportsByCityTest() {
		StorageHandler.emptyAirports();
		StorageHandler.createRandomAirports(10);
		
		List<Airport> airports = facade.findAirportsByCity(
				StorageHandler.airports
					.get(0)
					.getAddress()
					.getCity()
			);
		
		assertNotNull(airports);
		assertFalse(airports.isEmpty());
	}
	
	@Test
	void findAirlinesByAirportCode() {
		StorageHandler.emptyAirports();
		StorageHandler.createRandomAirports(15);
		
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

		List<Airline> airlines = facade.findAirlinesByAirportCode(StorageHandler.airports.get(0).getCode());
		assertNotNull(airlines);
		assertFalse(airlines.isEmpty());
			
	}
}
