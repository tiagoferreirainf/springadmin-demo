package tf.springadmindemo.wagecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WageCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WageCalculatorApplication.class, args);
	}
}
