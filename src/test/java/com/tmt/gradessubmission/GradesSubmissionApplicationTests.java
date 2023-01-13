package com.tmt.gradessubmission;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GradesSubmissionApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}


	@Test 
	public void testGetForm() throws Exception {
		//build mock "get request" on path / with optional id as per expected by the getForm handler
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");
		
		mockMvc.perform(request)		//then assert the status view and model as below
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"))
			.andExpect(model().attributeExists("grade"));
	}

	@Test
	public void testHandleSubmit() throws Exception {
		//build mock "post request" on path /handleSubmit with 
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
			.param("name", "Amina")	//the handler expects payload for the empty grade obj it creates
			.param("subject", "Java")
			.param("score", "A+");
			 
		mockMvc.perform(request)
			.andExpect(status().is3xxRedirection())	//expects to be redirected
			.andExpect(redirectedUrl("/grades"));	//expects to be redirected to this path
	}

	@Test
	public void testUnsuccessfulSubmission() throws Exception {
		//test handLeSubmit with invalid data
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
		.param("name", "   ")	//the handler expects payload for the empty grade obj it creates
		.param("subject", "   ")
		.param("score", "R+");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"));
	}

	@Test 
	public void testGetGradesPage() throws Exception {
		//build mock "get request" on path /grades 
		RequestBuilder request = MockMvcRequestBuilders.get("/grades");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("grades"))
			.andExpect(model().attributeExists("grades"));
	}

}
