package com.jachs.spring_r2dbc;

import java.time.Duration;
import java.util.Map;

import com.jachs.spring_r2dbc.entity.Users;
import org.junit.Before;
import org.junit.Test;
import org.springframework.r2dbc.core.DatabaseClient;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import reactor.core.publisher.Flux;

/***
 * 
 * @author zhanchaohan
 *
 */
public class MySqlPoolTest {
	DatabaseClient db;
	
	@Before
	public void init() {
		MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration.builder()
                .host("127.0.0.1")
                .port(3306)
                .database("alarm")
                .username("root")
                .password("")
                .build();

        ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder()
                .connectionFactory(MySqlConnectionFactory.from(configuration))
                .maxSize(10)
                //.maxIdleTime(Duration.ofSeconds(1))
                //.maxLifeTime(Duration.ofSeconds(100))
                .build();
        
        db=  DatabaseClient.create(new ConnectionPool(poolConfiguration));
//       db= DatabaseClient.builder().connectionFactory(new ConnectionPool(poolConfiguration)).build();
	}
	@Test
	public void test1() {
		Flux<Map<String, Object>>pList=db.sql("select * from Users").fetch().all();

		pList.doOnNext(n->{
			System.out.println("next");
		});
		pList.subscribe(a->{
			a.keySet().forEach(key->{
				System.out.println(key+"\t"+a.get(key));
			});
		});
		pList.blockFirst();
	}
}
