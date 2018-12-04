package cn.bw.fitzboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
@Import(RepositoryRestMvcAutoConfiguration.class)//enable repository restful
public class FitzBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitzBootApplication.class, args);
        System.out.println("Hello");
    }
}
