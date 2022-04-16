package com.example.EmployeeData;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.EmployeeData.dataEntities.User;
import com.example.EmployeeData.repo.UserRepository;
 
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepo;
    
    private User user;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
    
    public User updatedetails(User user,long id) {
    	@SuppressWarnings("unused")
		User users = userRepo.findById(id).get();
    	
    	if(Objects.nonNull(user.getFirstname()) && !" ".equalsIgnoreCase(user.getFirstname())) {
    		user.setFirstname(user.getFirstname());
    	}
		return users;
	}
    
    public User updatedetails1(User user,long id) {
    	@SuppressWarnings("unused")
		User users = userRepo.findById(id).get();
    	
    	if(Objects.nonNull(user.getLastname()) && !" ".equalsIgnoreCase(user.getLastname())) {
    		user.setLastname(user.getLastname());
    	}
		return users;
	}
    
    
    
    public User updatedetails2(User user,long id) {
    	@SuppressWarnings("unused")
		User users = userRepo.findById(id).get();
    	
    	if(Objects.nonNull(user.getAddress()) && !" ".equalsIgnoreCase(user.getAddress())) {
    		user.setAddress(user.getAddress());
    	}
		return users;
	}
    public User updatedetails4(User user,long id) {
    	@SuppressWarnings("unused")
		User users = userRepo.findById(id).get();
    	
    	if(Objects.nonNull(user.getPassword()) && !" ".equalsIgnoreCase(user.getPassword())) {
    		user.setPassword(user.getPassword());
    	}
		return users;
	}
    
    public User updatedetails5(User user,long id) {
    	@SuppressWarnings("unused")
		User users = userRepo.findById(id).get();
    	
    	if(Objects.nonNull(user.getEmail()) && !" ".equalsIgnoreCase(user.getEmail())) {
    		user.setEmail(user.getEmail());
    	}
		return users;
	}
    
    
    
    
    public void DeleteUserById(long id) {
    	userRepo.deleteById(id);
    	
	}
}
