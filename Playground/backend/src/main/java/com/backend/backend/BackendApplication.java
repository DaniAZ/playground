package com.backend.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Locale;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


	@Bean
    CommandLineRunner init(TransactionsService transactionsService) {
        return args -> {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.US);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));           
			String time = sdf.format(new Date()); 

			Transactions transaction1 = new Transactions(1200.00, time);
			Transactions transaction2 = new Transactions(1300.00, time);

			transactionsService.saveTransaction(transaction1);
			transactionsService.saveTransaction(transaction2);

        };
    }
}
