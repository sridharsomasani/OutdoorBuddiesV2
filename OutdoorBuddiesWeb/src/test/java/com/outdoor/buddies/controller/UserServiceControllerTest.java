package com.outdoor.buddies.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outdoor.buddies.jpa.entity.UserProfile;
import com.outdoor.buddies.repository.UserProfileRepository;
import com.outdoor.buddies.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = UserServiceController.class)
@AutoConfigureMockMvc(secure = false)
public class UserServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserServiceImpl userServiceImplMock;

	@Mock
	private UserProfileRepository userRepository;
	@Mock
	private UserProfile userProfile;
	@InjectMocks
	private UserServiceController userServiceController;

	UserProfile user1;

	@Before
	public void setUpUser() throws Exception {

	}

	@Test
	public void testExistingUserProfile() throws Exception {
		user1 = new UserProfile();
		user1.setUserId(1L);
		user1.setFirstName("Andy");
		user1.setLastName("Mujje");
		user1.setEmailId("a.m@gmail.com");
		assertThat(this.userServiceImplMock).isNotNull();
		when(userServiceImplMock.findUser(1L)).thenReturn(user1);

		MvcResult result = mockMvc.perform(get("/api/v1/users/{userId}/", 1)).andExpect(status().isOk()).andReturn();

		MockHttpServletResponse mockResponse = result.getResponse();
		assertThat(mockResponse.getContentType()).isEqualTo("application/json;charset=UTF-8");

		Collection<String> responseHeaders = mockResponse.getHeaderNames();
		assertNotNull(responseHeaders);
		assertEquals(1, responseHeaders.size());
		assertEquals("Check for Content-Type header", "Content-Type", responseHeaders.iterator().next());
		String responseAsString = mockResponse.getContentAsString();
		assertTrue(responseAsString.contains("Andy"));

		verify(userServiceImplMock, times(1)).findUser(1L);

	}

	@Test
	public void addNewUserProfileTest() throws Exception {

		UserProfile user2 = new UserProfile();
		user2.setFirstName("Madhu");
		user2.setLastName("Korepu");
		user2.setEmailId("m.k@gmail.com");
		user2.setAddress("Lafayette");
		user2.setDisplayName("mk");
		user2.setMobileNumber("123456789");
		user2.setPassword("password");
		user2.setUserName("mk");
		user2.setGender("M");
		LocalDate dob = LocalDate.of(1989, 07, 1);
		user2.setDob(Date.valueOf(dob));

		mockMvc.perform(post("/api/v1/users/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user2)))
				.andExpect(status().isCreated());

	}

	@Test
	public void updateUserProfileTest() throws Exception {
		user1 = new UserProfile();
		user1.setUserId(1L);
		user1.setFirstName("Andy");
		user1.setLastName("Mujje");
		user1.setEmailId("a.m@gmail.com");
		when(userServiceImplMock.findUser(1L)).thenReturn(user1);

		mockMvc.perform(get("/api/v1/users/{userId}/", 1)).andExpect(status().isOk()).andReturn();

		UserProfile user3 = new UserProfile();
		user3.setFirstName("Sandy");
		user3.setLastName("Mujje");
		user3.setEmailId("a.m@gmail.com");
		user3.setAddress("Lafayette");
		user3.setDisplayName("mk");
		user3.setMobileNumber("123456789");
		user3.setPassword("password");
		user3.setUserName("mk");
		user3.setGender("M");
		LocalDate dob = LocalDate.of(1989, 07, 1);
		user3.setDob(Date.valueOf(dob));

		when(userServiceImplMock.findUser(user1.getUserId())).thenReturn(user1);

		mockMvc.perform(put("/api/v1/users/{id}", user1.getUserId()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user3))).andExpect(status().isOk());

		verify(userServiceImplMock, times(1)).findUser(user1.getUserId());
		// verify(userServiceImplMock, times(1)).updateUser(user1,user1.getUserId());
		// to do: userprofile objects are different. find way to verify this
	}

	@Test
	public void deleteUserProfileTest() throws Exception {
		user1 = new UserProfile();
		user1.setUserId(1L);
		user1.setFirstName("Andy");
		user1.setLastName("Mujje");
		user1.setEmailId("a.m@gmail.com");
		when(userServiceImplMock.findUser(1L)).thenReturn(user1);
		// verified with status code 204 as no response is received
		mockMvc.perform(delete("/api/v1/users/{id}", user1.getUserId())).andExpect(status().is2xxSuccessful());

		verify(userServiceImplMock, times(1)).deleteUser(user1.getUserId());

	}
	
	@Test
    public void updateUserNotFound() throws Exception {
		user1 = new UserProfile();
		user1.setUserId(1L);
		user1.setFirstName("Andy");
		user1.setLastName("Mujje");
		user1.setEmailId("a.m@gmail.com");
		when(userServiceImplMock.findUser(user1.getUserId())).thenReturn(null);
		UserProfile user3 = new UserProfile();
		user3.setFirstName("Sandy");
		user3.setLastName("Mujje");
		user3.setEmailId("a.m@gmail.com");
        mockMvc.perform(put("/api/v1/users/{id}", user1.getUserId()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user3)))
                .andExpect(status().is4xxClientError());

    }

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
