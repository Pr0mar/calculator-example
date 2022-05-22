package marcin.lenda.calculator.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import marcin.lenda.calculator.example.model.Input;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void endpoint_should_return_8() throws Exception {
        //given
        final Input input = Input.builder().value("1 + 3 + 4").build();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        final ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        final String requestJson=ow.writeValueAsString(input);

        //then
        this.mockMvc
                .perform(post("/calculator")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andDo(print())
                .andExpect(content().string(containsString("8")));
    }
}
