package com.jachs.expression;

import com.jachs.expression.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @author zhanchaohan
 */
@Configuration
public class BeanConfig {

    @Bean
    public User user() {
        return new User("lisi");
    }
}