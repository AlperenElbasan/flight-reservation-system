package edu.miu.cs.cs401.project;

import edu.miu.cs.cs401.project.domain.Passenger;
import edu.miu.cs.cs401.project.helpers.StorageHandler;
import edu.miu.cs.cs401.project.service.AdminFacadeImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AdminFacadeImplTest {
	AdminFacadeImpl adminFacade = new AdminFacadeImpl(); 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreatePassenger() throws ParseException {
		Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1989");
		adminFacade.createPassenger("firstName", "lastName", birthday, "abc@gmail.com", StorageHandler.getRandomAddress());

		assertEquals(StorageHandler.passengers.size(), 1);
		assertNotNull(StorageHandler.passengers.get(0));
	}

	@Test
	public void testUpdatePassengerFirstName() throws ParseException {
		Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1989");
		Passenger p = new Passenger("firstName", "lastName", birthday, "abc@gmail.com", StorageHandler.getRandomAddress());
		adminFacade.updatePassengerFirstName(p, "firstname_update");
		assertEquals(p.getFirstName(), "firstname_update");
	}

	@Test
	public void testUpdatePassengerLastName() throws ParseException {
		Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1989");
		Passenger p = new Passenger("firstName", "lastName", birthday, "abc@gmail.com", StorageHandler.getRandomAddress());
		adminFacade.updatePassengerLastName(p, "lastName_update");
		assertEquals(p.getLastName(), "lastName_update");
	}

	@Test
	public void testUpdatePassengerEmail() throws ParseException {
		Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1989");
		Passenger p = new Passenger("firstName", "lastName", birthday, "abc@gmail.com", StorageHandler.getRandomAddress());
		adminFacade.updatePassengerEmail(p, "abc_updated@gmail.com");
		assertEquals(p.getEmail(), "abc_updated@gmail.com");
	}

	@Test
	public void testDeletePassenger() throws ParseException {
		Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1989");
		adminFacade.createPassenger("firstName", "lastName", birthday, "abc@gmail.com", StorageHandler.getRandomAddress());
		adminFacade.deletePassenger(StorageHandler.passengers.get(0));
		assertEquals(StorageHandler.passengers.size(), 0);
	}

	@Test
	public void testDeleteAgent() {
	}

	@Test
	public void testReadAirport() {
	}

	@Test
	public void testUpdateAirportName() {
	}

	@Test
	public void testUpdateAirportCode() {
	}

	@Test
	public void testUpdateAirportAddress() {
	}

	@Test
	public void testDeleteAirport() {
	}

	@Test
	public void testReadAirline() {
	}

	@Test
	public void testUpdateAirlineName() {
	}

	@Test
	public void testUpdateAirlineCode() {
	}

	@Test
	public void testUpdateAirlinegetHistory() {
	}

	@Test
	public void testDeleteAirline() {
	}
}
