package ar.dev.maxisandoval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BancoApiJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancoApiJwtApplication.class, args);
    }

}