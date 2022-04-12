package tn.esponline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esponline.entities.Role;
import tn.esponline.entities.User;
import tn.esponline.service.IUserService;

@SpringBootTest
class TimesheetDevopsApplicationTests {
	@Autowired
	IUserService userService;

	private static final Logger LOG = LogManager.getLogger(TimesheetDevopsApplicationTests.class);


	public static User user;

	@Test
	void testAddUser(){
		User user = new User();
		user.setDateNaissance(new Date());
		user.setLastName("Aziz");
		user.setRole(Role.INGENIEUR);
		User res = userService.addUser(user);
		this.user = res;
		assertNotEquals(res , null);
		LOG.info("user created successfully");

	}

	@Test
	void testUpdateUser(){
		this.user.setLastName("Njaimi");
		this.user = userService.updateUser(this.user);
		assertEquals(this.user.getLastName(),"Njaimi");
		LOG.info("user updated successfully");
	}

	@Test
	void testRetrieveAllUsers() {
		List<User> listUsers= userService.retrieveAllUsers();
		assertNotEquals(listUsers.size(), 0);
		LOG.info("users retrieved successfully");

	}

	@Test
	void testDeleteUser(){
		userService.deleteUser(this.user.getId().toString());
		long length = userService.retrieveAllUsers().stream().filter(elt -> elt.getId() == this.user.getId()).count();
		assertEquals(length,0);
		LOG.info("user deleted successfully");
	}
}
