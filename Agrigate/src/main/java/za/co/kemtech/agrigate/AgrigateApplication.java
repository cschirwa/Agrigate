package za.co.kemtech.agrigate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import za.co.kemtech.agrigate.entity.userdomain.Role;
import za.co.kemtech.agrigate.repositories.RoleRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class AgrigateApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgrigateApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(RoleRepository roleRepository){
        return args -> {
            if(!roleRepository.findByName("USER").isPresent()){
                roleRepository.save(Role.builder().name("USER").build());
            }
        };
    };
}
