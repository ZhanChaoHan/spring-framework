package com.jachs.spring_transactional.db;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jachs.spring_transactional.dao.SoftwareDao;
import com.jachs.spring_transactional.entity.Computer;
import com.jachs.spring_transactional.entity.Software;

/***
 * 手动开启事务
 * @author zhanchaohan
 *
 */
public class SoftwareDaoTest {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("db/spring-advice-transactional.xml");

    @Test
    public void initData(){
        Computer cp=new Computer();
        cp.setComputerId("S_one");
        cp.setComputerName("Dell");
        cp.setComputerPrice(98745L);
        cp.setMadeTime(new Date());
        
        Software sw=new Software();
        sw.setCId("S_one");
        sw.setSoftWareId("Wechar");
        sw.setSoftWareName("微信");
        
        SoftwareDao sDao=ctx.getBean("sDao",SoftwareDao.class);
        
        sDao.saveCpAndSw(cp,sw);
    }
    //测试Id不一致, @Transactional自动事务是否生效
    @Test
    public void test2(){
        Computer cp=new Computer();
        cp.setComputerId("S_KA");
        cp.setComputerName("Davil");
        cp.setComputerPrice(15487L);
        cp.setMadeTime(new Date());

        Software sw=new Software();
        sw.setCId("S_None");
        sw.setSoftWareId("QMT");
        sw.setSoftWareName("皮皮虾");

        SoftwareDao sDao=ctx.getBean("sDao",SoftwareDao.class);

        sDao.saveCpAndSw(cp,sw);
    }
}
