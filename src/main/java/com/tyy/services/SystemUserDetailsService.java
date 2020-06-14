package com.tyy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.tyy.dao.AuthDAO;
import com.tyy.dao.EmployeeDAO;
import com.tyy.dao.ManagerEmployeeRelationDAO;
import com.tyy.dao.UserDAO;
import com.tyy.dto.UserDTO;
import com.tyy.dto.UserDTOforAdmin;
import com.tyy.entities.Auth;
import com.tyy.entities.Employee;
import com.tyy.entities.ManagerEmployeeRelation;
import com.tyy.entities.User;

@Service
public class SystemUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	private UserDAO urepo;
	
	@Autowired
	private AuthDAO arepo;
	
	@Autowired
	private ManagerEmployeeRelationDAO MERrepo;
	
	@Autowired
	private EmployeeDAO erepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = jdbcUserDetailsManager.loadUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("No user found with username: "+username);
		}
		return user;
	}
	
	public boolean registerUser(UserDTO newUser, String type) {
		if(jdbcUserDetailsManager.userExists(newUser.getUsername())) {
			return false;
		}
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority("ROLE_"+type.toUpperCase()));
		newUser.setAuthorities(auth);
		String oldpass = newUser.getPassword();
		newUser.setPassword(encoder.encode(oldpass));
		jdbcUserDetailsManager.createUser(newUser);
		return true;
	}
	
	public String getUsername() {
		String username="";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		return username;
	}
	
	public List<UserDTOforAdmin> findAllUserWithAuth(){
		return urepo.findAllUserWithAuth();
	}
	
	public UserDTOforAdmin findUserWithAuth(String username){
		return urepo.findUserWithAuth(username);
	}
	
	public List<User> findAllByUsername(String username) {
		return urepo.findAllByUsername(username);
	}
	
	public void save(User user) {
		urepo.save(user);
	}
	
	public void checkIsManager(Employee e) {
		if(findUserWithAuth(e.getUsername()).getAuth().equals("ROLE_EMPLOYEE")) {
			e.setIsmanager(0);
		}
		else {
			e.setIsmanager(1);
		}
		erepo.save(e);
	}
	
	public void changeEnable(String username) {
		User tmp = findAllByUsername(username).get(0);
		if(tmp.getEnabled()==0) {
			tmp.setEnabled(1);
		}
		else {
			tmp.setEnabled(0);
		}
		save(tmp);
	}
	
	public void changePosition(String username) {
		Auth tmp = arepo.findAllByUsername(username).get(0);
		if(tmp.getAuthority().equals("ROLE_MANAGER")) {
			tmp.setAuthority("ROLE_EMPLOYEE");
			try {
				int id = erepo.findByUsername(username).getId();
				List<ManagerEmployeeRelation> tmp1 = MERrepo.findAllByManagerid(id);
				for(ManagerEmployeeRelation r:tmp1) {
					MERrepo.delete(r);
				}
			}catch(Exception e) {
			}
			arepo.save(tmp);
		}
		else {
			tmp.setAuthority("ROLE_MANAGER");
			try {
				int id = erepo.findByUsername(username).getId();
				List<ManagerEmployeeRelation> tmp1 = MERrepo.findAllByEmployeeid(id);
				for(ManagerEmployeeRelation r:tmp1) {
					MERrepo.delete(r);
				}
			}catch(Exception e){
			}
			arepo.save(tmp);
		}
	}
	
	public void resetPassword(String username) {
		User tmp = findAllByUsername(username).get(0);
		tmp.setPassword(encoder.encode("00000000"));
		save(tmp);
	}
	
	public void changePassword(String newpass) {
		User tmp = findAllByUsername(getUsername()).get(0);
		tmp.setPassword(encoder.encode(newpass));
		save(tmp);
	}

}

