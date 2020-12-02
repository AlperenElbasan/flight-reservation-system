package edu.miu.cs.cs401.project;

import edu.miu.cs.cs401.project.constants.ReservationStatus;
import edu.miu.cs.cs401.project.domain.*;
import edu.miu.cs.cs401.project.helpers.StorageHandler;
import edu.miu.cs.cs401.project.service.ReservationSystemFacade;
import edu.miu.cs.cs401.project.service.ReservationSystemFacadeImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

	// For agent reservation
	@Test
	public void testCreateReservation() {
		Agent agent = new Agent();
		agent.addPassenger(StorageHandler.getRandomPassenger(1));
		agent.addPassenger(StorageHandler.getRandomPassenger(2));
		List<Flight> flights = StorageHandler.generateListFlightInstance(5);
		Passenger passenger = StorageHandler.getRandomPassenger(2);

		Reservation re = facade.createReservation(agent, passenger, flights);
		assertNotNull(re);
		assertEquals(re.getAgentId(), agent.getUuid());
		assertEquals(StorageHandler.getReservationByCode(re.getReservationCode()), re );
		assertEquals(passenger.getReservations().get(0), re );
		assertEquals(re.getTickets().size(), 5 );
	}

	// For passenger reservation
	@Test
	public void testTestCreateReservation() {
		List<Flight> flights = StorageHandler.generateListFlightInstance(5);
		Passenger passenger = StorageHandler.getRandomPassenger(2);

		Reservation re = facade.createReservation(passenger, flights);
		assertNotNull(re);
		assertEquals(StorageHandler.getReservationByCode(re.getReservationCode()), re );
		assertEquals(passenger.getReservations().get(0), re );
		assertEquals(re.getTickets().size(), 5 );
	}

	@Test
	public void testConfirmReservation() {
		// generate a reservation
		Agent agent = new Agent();
		agent.addPassenger(StorageHandler.getRandomPassenger(1));
		agent.addPassenger(StorageHandler.getRandomPassenger(2));
		List<Flight> flights = StorageHandler.generateListFlightInstance(5);
		Passenger passenger = StorageHandler.getRandomPassenger(2);
		Reservation re = facade.createReservation(agent, passenger, flights);
		re.confirm();

		assertNotNull(re);
		assertEquals(re.getStatus(), ReservationStatus.CONFIRM_PURCHASE);
	}

	@Test
	public void testCancelReservation() {
		// generate a reservation
		Agent agent = new Agent();
		agent.addPassenger(StorageHandler.getRandomPassenger(1));
		agent.addPassenger(StorageHandler.getRandomPassenger(2));
		List<Flight> flights = StorageHandler.generateListFlightInstance(5);
		Passenger passenger = StorageHandler.getRandomPassenger(2);
		Reservation re = facade.createReservation(agent, passenger, flights);
		re.cancel();

		assertNotNull(re);
		assertEquals(re.getStatus(), ReservationStatus.CANCEL);
	}
}
