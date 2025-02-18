package br.com.fiap.api_patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiPatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPatientApplication.class, args);
    }

}
