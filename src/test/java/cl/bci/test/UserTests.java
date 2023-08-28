package cl.bci.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.bci.api.dto.UsersDTO;
import cl.bci.api.model.Users;
import cl.bci.api.service.UserService;

@SpringBootTest
//@SpringBootConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
public class UserTests {
	
	private static final String REQUEST_BASE_PATH = "src/test/java/cl/bci/test/resources/";
	
	
//	@Bef
	
	@MockBean
	private UserService userService;
	
	
	@Test
	public void test01SaveUser() throws Exception{
		
		String jsonRequest = this.readFile("test01SaveUser.json");
		
		UsersDTO dto = new ObjectMapper().readValue(jsonRequest, UsersDTO.class);
		
		
		Users userEntity = userService.save(dto);
		
		assertTrue(userEntity.getId() instanceof UUID);
		assertTrue(!userEntity.getPhoneList().isEmpty());
		
	}
	
	
	public String readFile(String path) {
		try {
			File file = ResourceUtils.getFile(REQUEST_BASE_PATH+path);
			return new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
