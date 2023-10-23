package com.jachs.spring_core.cache;

import com.jachs.spring_core.bean.People;
import org.junit.Test;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/***
 * @author zhanchaohan
 */
@Service("springCacheService")
public class SpringCacheService {

    @Cacheable(value="testcache",key="#pName")
    public People findPeopleByName(String pName) throws Exception{
       return getPeople(pName);
    }

    public People getPeople(String pName){
        People peo=new People();

        peo.setName(pName);
        peo.setAge(20);
        peo.setPhoneNum(123765632L);

        return peo;
    }
}
