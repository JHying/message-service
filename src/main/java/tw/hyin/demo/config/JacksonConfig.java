package tw.hyin.demo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import tw.hyin.java.utils.JsonUtil;
import tw.hyin.java.utils.Log;

/**
 * JSON 轉換全域設定
 *
 * @author H-yin
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Include.NON_NULL 屬性為 NULL 時不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 允許出現單引號
        objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        Log.info("Initialized JacksonConfig complete.");
        //修改常用工具的 mapper 設定
        JsonUtil.setMapper(objectMapper);
        return objectMapper;
    }
}
