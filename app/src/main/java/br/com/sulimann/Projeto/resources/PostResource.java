package br.com.sulimann.Projeto.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sulimann.Projeto.dto.CommentDTO;
import br.com.sulimann.Projeto.dto.PostDTO;
import br.com.sulimann.Projeto.model.CommentModel;
import br.com.sulimann.Projeto.model.PostModel;
import br.com.sulimann.Projeto.resources.util.URL;
import br.com.sulimann.Projeto.services.CommentService;
import br.com.sulimann.Projeto.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    
    @Autowired
    private PostService service;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<PostModel>> findAll(){
        List<PostModel> list = service.findAll();      
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{idPost}")
    public ResponseEntity<PostModel> findById(@PathVariable String idPost) {
        PostModel obj = service.findById(idPost);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<PostModel> insert(@RequestBody PostDTO objDTO){
        PostModel obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idPost}").buildAndExpand(obj.getIdPost()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{idPost}")
    public ResponseEntity<Void> delete(@PathVariable String idPost){
        service.delete(idPost);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idPost}")
    public ResponseEntity<PostModel> update(@PathVariable String idPost, @RequestBody PostDTO obj){
        PostModel newObj = service.fromDTO(obj);
        newObj.setIdPost(idPost);
        newObj = service.update(idPost, newObj);
        return ResponseEntity.ok().body(newObj);
    }

    @GetMapping(value = "/{idPost}/comments")
    public ResponseEntity<List<CommentModel>> findComents(@PathVariable String idPost){
        PostModel obj = service.findById(idPost);
        return ResponseEntity.ok().body(obj.getComments());
    }

    @GetMapping(value = "/{idPost}/{idComment}")
    public ResponseEntity<CommentModel> findCommentById(@PathVariable String idPost, @PathVariable String idComment) {
        findById(idPost);
        CommentModel obj = commentService.findById(idComment);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/{idPost}")
    public ResponseEntity<CommentModel> insertComment(@RequestBody CommentDTO objDTO, @PathVariable String idPost){
        CommentModel obj = commentService.fromDTO(objDTO);
        obj = commentService.insert(obj);
        PostModel post = service.findById(idPost);
        post.addComment(obj);
        service.updateComment(idPost, post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idComment}").buildAndExpand(obj.getIdComment()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{idPost}/{idComment}")
    public ResponseEntity<Void> deleteComment(@PathVariable String idPost, @PathVariable String idComment){
        PostModel obj = service.findById(idPost);
        CommentModel comment = commentService.findById(idComment);
        obj.removeComment(comment);
        commentService.delete(idComment);
        service.updateComment(idPost, obj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<PostModel>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<PostModel> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<PostModel>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text, @RequestParam(value = "minDate", defaultValue = "") String minDate, @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        text = URL.decodeParam(text);
        Date min = URL.converterDate(minDate, new Date(0L));
        Date max = URL.converterDate(maxDate, new Date());
        List<PostModel> list = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}
