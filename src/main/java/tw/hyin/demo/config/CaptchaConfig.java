/**
 * 
 */
package tw.hyin.demo.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import tw.hyin.java.utils.security.CaptchaUtil;

/**
 * @author YingHan
 * @since 2022-03-23
 * 
 * @Description 
 */
@Configuration
public class CaptchaConfig {
	
	private static final Cache<String, String> LOCAL_CACHE = CacheBuilder.newBuilder().maximumSize(1000)
			.expireAfterAccess(5, TimeUnit.MINUTES).build();
	
	@Bean
    public CaptchaUtil captchaUtil(){
		CaptchaUtil captchaUtil = new CaptchaUtil();
		captchaUtil.setCache(LOCAL_CACHE);
        return captchaUtil;
    }

}
