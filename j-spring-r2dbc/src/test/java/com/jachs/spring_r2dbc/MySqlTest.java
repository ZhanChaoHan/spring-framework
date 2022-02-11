package com.jachs.spring_r2dbc;

import java.time.ZoneId;

import com.jachs.spring_r2dbc.entity.Users;
import org.junit.Before;
import org.junit.Test;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Statement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/***
 * 
 * @author zhanchaohan
 *
 */
public class MySqlTest {
	private ConnectionFactory connectionFactory;
	private Mono<Connection> connectionMono;
	
	@Before
	public void init () {
		MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration.builder()
			    .host("127.0.0.1")
			    .user("root")
			    .port(3306) // optional, default 3306
			    .password("") // optional, default null, null means has no password
			    .database("alarm") // optional, default null, null means not specifying the database
			    .serverZoneId(ZoneId.of("UTC+8")) // optional, default null, null means query server time zone when connection init
//			    .connectTimeout(Duration.ofSeconds(3)) // optional, default null, null means no timeout
//			    .sslMode(SslMode.VERIFY_IDENTITY) // optional, default SslMode.PREFERRED
//			    .sslCa("/path/to/mysql/ca.pem") // required when sslMode is VERIFY_CA or VERIFY_IDENTITY, default null, null means has no server CA cert
//			    .sslCert("/path/to/mysql/client-cert.pem") // optional, default has no client SSL certificate
//			    .sslKey("/path/to/mysql/client-key.pem") // optional, default has no client SSL key
//			    .sslKeyPassword("key-pem-password-in-here") // optional, default has no client SSL key password
//			    .tlsVersion(TlsVersions.TLS1_3, TlsVersions.TLS1_2, TlsVersions.TLS1_1) // optional, default is auto-selected by the server
//			    .sslHostnameVerifier(MyVerifier.INSTANCE) // optional, default is null, null means use standard verifier
//			    .sslContextBuilderCustomizer(MyCustomizer.INSTANCE) // optional, default is no-op customizer
//			    .zeroDateOption(ZeroDateOption.USE_NULL) // optional, default ZeroDateOption.USE_NULL
//			    .useServerPrepareStatement() // Use server-preparing statements, default use client-preparing statements
//			    .tcpKeepAlive(true) // optional, controls TCP Keep Alive, default is false
//			    .tcpNoDelay(true) // optional, controls TCP No Delay, default is false
//			    .autodetectExtensions(false) // optional, controls extension auto-detect, default is true
//			    .extendWith(MyExtension.INSTANCE) // optional, manual extend an extension into extensions, default using auto-detect
			    .build();
		
			connectionFactory = MySqlConnectionFactory.from(configuration);
			
			// Creating a Mono using Project Reactor
			connectionMono = Mono.from(connectionFactory.create());
	}

	@Test
	public void test1() {
		Flux.from(connectionFactory.create()).flatMap(c->Flux.from(c.createBatch()
				.add("drop table if exists Users")
                .add("create table Users(" +
                        "id VARCHAR(80)," +
                        "firstname varchar(80) not null," +
                        "lastname varchar(80) not null)")
                .add("insert into Users(id,firstname,lastname) values(1,'flydean','ma')")
                .add("insert into Users(id,firstname,lastname) values(2,'jacken','yu')")
				.execute()).doFinally((st)->c.close())).log()
        		.blockLast();
		
	}
    @Test
    public void test2() {
        Flux<Users>  users=Mono.from(connectionFactory.create())
                .flatMap((c) -> Mono.from(c.createStatement("select id,firstname,lastname from users")
                                .execute())
                        .doFinally((st) -> close(c)))
                .flatMapMany(result -> Flux.from(result.map((row, meta) -> {
                    Users acc = new Users();
                    acc.setId(row.get("id", String.class));
                    acc.setFirstname(row.get("firstname", String.class));
                    acc.setLastname(row.get("lastname", String.class));
                    return acc;
                })));

		Users first=users.blockFirst();
		System.out.println(first.getFirstname());
		System.out.println(first.getLastname());
    }

	private void close(Connection c) {
		c.close();
	}
}
