package br.com.sulimann.Projeto.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.sulimann.Projeto.dto.AuthorDTO;
import br.com.sulimann.Projeto.model.CommentModel;
import br.com.sulimann.Projeto.model.PostModel;
import br.com.sulimann.Projeto.model.UserModel;
import br.com.sulimann.Projeto.repository.CommentRepository;
import br.com.sulimann.Projeto.repository.PostRepository;
import br.com.sulimann.Projeto.repository.UserRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        UserModel maria = new UserModel(null, "Maria Brown", "maria@gmail.com");
        UserModel alex = new UserModel(null, "Alex Green", "alex@gmail.com");
        UserModel bob = new UserModel(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        PostModel post1 = new PostModel(null, Instant.now(), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        PostModel post2 = new PostModel(null, Instant.now(), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentModel c1 = new CommentModel(null, "Boa viagem mano!", Instant.now(), new AuthorDTO(alex));
        CommentModel c2 = new CommentModel(null, "Aproveite!", Instant.now(), new AuthorDTO(bob));
        CommentModel c3 = new CommentModel(null, "Tenha um ótimo dia!", Instant.now(), new AuthorDTO(alex));

        commentRepository.saveAll(Arrays.asList(c1, c2, c3));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
        
    }
    
}
