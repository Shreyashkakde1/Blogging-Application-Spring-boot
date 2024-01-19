package com.shreyash.blog;

import com.shreyash.blog.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Test
	void contextLoads() {
	}

	@Test
	public void repositoryTest(){
		String name = userRepository.getClass().getName();
		String packageName = userRepository.getClass().getPackageName();
		System.out.println(name);
		System.out.println(packageName);

	}

}
