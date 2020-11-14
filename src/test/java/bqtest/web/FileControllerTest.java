package bqtest.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FileControllerTest {

	@Autowired
	private MockMvc mockMvc;

	
	@Test
	@DisplayName("Test Load Data method")
	public void testLoadData() throws Exception {

		this.mockMvc.perform(get("/api/load-data"))
		.andDo(print()).andExpect(status().isOk());
	}
}
