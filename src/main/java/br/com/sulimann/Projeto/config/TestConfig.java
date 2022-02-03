package br.com.sulimann.Projeto.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.sulimann.Projeto.model.UserModel;
import br.com.sulimann.Projeto.repository.UserRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        UserModel maria = new UserModel(null, "Maria Brown", "maria@gmail.com");
        UserModel alex = new UserModel(null, "Alex Green", "alex@gmail.com");
        UserModel bob = new UserModel(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        
    }
    
}
