package com.jachs.spring_core.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jachs.spring_core.bean.People;
/***
 * @author zhanchaohan
 */
@Configuration
public class AnnConfiguer {

    @Bean
    public People initPeople(){
        People p=new People();

        p.setPhoneNum(123456L);
        p.setAge(12);
        p.setName("j");
        return p;
    }
}
