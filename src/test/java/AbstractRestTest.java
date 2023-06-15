import tw.hyin.demo.MessageServiceApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author yingHan on 2021.
 */
//@RunWith(SpringRunner.class)//讓測試運行於 spring 環境
@ExtendWith(SpringExtension.class)//junit 5 需使用 extendWith 替代 RunWith (SpringExtension 是 spring 5 中 spring test 的集成)
@SpringBootTest(classes = MessageServiceApplication.class)
@WebAppConfiguration
public abstract class AbstractRestTest {

    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
