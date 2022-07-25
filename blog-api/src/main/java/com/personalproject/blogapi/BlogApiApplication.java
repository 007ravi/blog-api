package com.personalproject.blogapi;

import com.personalproject.blogapi.constants.AppConstants;
import com.personalproject.blogapi.models.Role;
import com.personalproject.blogapi.repository.RoleRepo;
import com.personalproject.blogapi.util.PasswordGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception{
		System.out.println(this.passwordEncoder.encode(args[0]));

		try{
			//crete role in starting if role is not in role table
			Role role= new Role();
			role .setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role1= new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ROLE_NORMAL");

			List<Role>roles=List.of(role,role1);

			List<Role>result=this.roleRepo.saveAll(roles);

			result.forEach(r->{
				System.out.println(r.getName());
			});
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

}
