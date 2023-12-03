package com.indatacore.test;

import com.indatacore.test.dto.RoleDTO;
import com.indatacore.test.dto.UserDTO;
import com.indatacore.test.entities.CSV;
import com.indatacore.test.repositories.CSVRepo;
import com.indatacore.test.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

//
//@Component
@EnableWebMvc
@SpringBootApplication
@EnableSwagger2
@CrossOrigin("*")
public class TestApplication extends SpringBootServletInitializer implements CommandLineRunner  {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TestApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Autowired
    CSVRepo csvRepo;

    @Bean
    public CommandLineRunner readCsv() {
        return args -> {
            List<CSV> objects = new ArrayList<>();
            try {

                String filePath = ResourceUtils.getFile("classpath:data.csv").getPath();

                BufferedReader br = new BufferedReader(new FileReader(filePath));
                // Skip the header line
                String headerLine = br.readLine();

                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    CSV obj = new CSV(null,values[1],Long.parseLong(values[2]), Double.parseDouble(values[3]) , values[4]); // Remplacez les indices par les colonnes appropriées du CSV
                    objects.add(obj);
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Faites quelque chose avec la liste d'objets, comme l'affichage
            for (CSV obj : objects) {
                csvRepo.save(obj);
            }
        };
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void run(String... args) throws Exception {


    }

//    @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole(new RoleDTO("USER"));
            accountService.addNewRole(new RoleDTO("ADMIN"));

            accountService.addNewUser(new UserDTO("user","user","user","user@user.com","userPassword"));
            accountService.addNewUser(new UserDTO("admin","admin","admin","admin@admin.com","adminPassword"));

            accountService.addRoleToUser("user@user.com","USER");
            accountService.addRoleToUser("admin@admin.com","USER");
            accountService.addRoleToUser("admin@admin.com","ADMIN");

        };

    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
