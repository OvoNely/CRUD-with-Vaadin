package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(CustomerRepository repository) {
        return args -> {
            repository.save(new Customer("Иван", "Иванов", "муж", LocalDate.of(1989, 01, 03)));
            repository.save(new Customer("Ирина", "Черепанова", "жен", LocalDate.of(1987, 07, 15)));
            repository.save(new Customer("Ольга", "Степанова", "жен", LocalDate.of(1994, 03, 14)));
            repository.save(new Customer("Сергей", "Александров", "муж", LocalDate.of(1981, 02, 27)));
            repository.save(new Customer("Михаил", "Королев", "муж", LocalDate.of(1980, 02, 29)));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer: repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            Customer customer = repository.findById(1L).get();
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            log.info("Customer found with findByLastNameStartsWithIgnoreCase(\"Иванов\"):");
            log.info("--------------------------------");
            for (Customer ivanov : repository.findByLastNameStartsWithIgnoreCase("Иванов")) {
                log.info(ivanov.toString());
            }
            log.info("");
        };
    }
}
