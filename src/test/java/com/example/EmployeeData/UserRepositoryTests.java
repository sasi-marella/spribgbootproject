package com.example.EmployeeData;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.EmployeeData.dataEntities.User;
import com.example.EmployeeData.repo.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	private User user;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser(){
		User user = new User();
		user.setEmail("abc@gmail.com");
		user.setPassword("abc123");
		user.setFirstname("abc");
		user.setLastname("123");
		
		User savedUser = repo.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());	
		
	}
	
	@Test
	public void whenDeleteById() {
		repo.deleteById(user.getId());
		assertThat(repo.count()).isEqualTo(1);
		
	}

}
