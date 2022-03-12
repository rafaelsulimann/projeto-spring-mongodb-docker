package br.com.sulimann.Projeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sulimann.Projeto.dto.CommentDTO;
import br.com.sulimann.Projeto.model.CommentModel;
import br.com.sulimann.Projeto.repository.CommentRepository;
import br.com.sulimann.Projeto.services.exceptions.NotFoundException;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public CommentModel findById(String id) {
        Optional<CommentModel> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("Comentário nao encontrado"));
    }

    public CommentModel insert(CommentModel obj){
        return repository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }    

    public CommentModel fromDTO(CommentDTO objDTO) {
        return new CommentModel(objDTO.getIdComment(), objDTO.getText(), objDTO.getDate(), objDTO.getAuthor());
    }
}
