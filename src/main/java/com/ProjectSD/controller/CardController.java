package com.ProjectSD.controller;

import static com.ProjectSD.controller.UsuarioController.id_USER;
import com.ProjectSD.model.Card;
import com.ProjectSD.repository.CardRepository;
import com.ProjectSD.service.CardService;
import java.time.LocalDateTime;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    private CardService cardService;

    @Autowired
    private CardRepository repository;

    // CardController
    @GetMapping("/user")
    public List<Document> findAllByUserId() {
        System.out.println("Id dentro do controller: "+ id_USER);
        return repository.findAllByUserId(id_USER);
    }

    @GetMapping("/flash")
    public Document findById() {
       Document card =  repository.findAllByTime(id_USER);
        System.out.println("Flash id_USER: "+ id_USER);
               
        return  card;
    }
    @GetMapping("/id_card/{id_card}")
    public Document findByIdCard(@PathVariable("id_card") String id_card){
        Document card = repository.findByIdCard(id_card);
     
        return card;
    }
    
  
    
    @GetMapping("/engname/{name}")
    public Document findByEngName(@PathVariable("name") String engName) {
        Document cards = repository.findByEngName(engName);
        return cards;
    }

    @GetMapping("/portname/{name}")
    public List<Document> findByPortName(@PathVariable("name") String portName) {
        List<Document> cards = repository.findByPortName(portName);
        return cards;
    }

    @PostMapping(path = "/postcard")
    public ResponseEntity<String> create(@RequestBody Card card) {
        System.out.println("Dentro do ");
        Document doc = new Document();
        System.out.println("ID_user: "+ id_USER);
        doc.append("id_card", repository.getLastCardId())
                .append("id_user", id_USER)
                .append("eng_name", card.getEngName())
                .append("eng_desc", card.getEngDesc())
                .append("port_name", card.getPortName())
                .append("port_desc", card.getPortDesc())
                .append("nota", card.getNota()).append("time", LocalDateTime.now());
        repository.save(doc);
        return ResponseEntity.status(HttpStatus.CREATED).body("Card created successfully!");
    }

    @PostMapping("/flash")
    public ResponseEntity<String> update(@RequestBody Card card) {
        Document doc = new Document();
        String id = card.getId();
        
        doc.append("id_card", id)
                .append("id_user", card.getUserId())
                .append("eng_name", card.getEngName())
                .append("eng_desc", card.getEngDesc())
                .append("port_name", card.getPortName())
                .append("port_desc", card.getPortDesc())
                .append("nota", card.getNota())
                .append("time", LocalDateTime.now());
        System.out.println("Update card: " + id +"\nNome: "+ card.toString());
        repository.update(id, doc);
        return ResponseEntity.ok("Card atualizado com sucesso!");
    }

    @DeleteMapping("postcard/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        repository.delete(id);
        return ResponseEntity.ok("Card deletado com sucesso!");
    }
}


/*
dificuldade para lembrar

1- Esqueci Totalmente - Muito Fraco
2- Lembrei pouco      - Fraco
3- Quase acertei      - Medio
4- Acertei quase tudo - Forte
5- Acertei Totalmente - Muito Forte

*/

/*
GET /cards: retorna uma lista de todos os cards.
GET /cards/{id}: retorna um card pelo seu id.
GET /cards/id_card/{id_card}: retorna um card pelo seu id_card.
GET /cards/engname/{name}: retorna um card pelo seu nome em inglês.
GET /cards/portname/{name}: retorna uma lista de cards pelo seu nome em português.
POST /cards/postcard: cria um novo card.
PUT /cards/{id}: atualiza um card existente pelo seu id.
DELETE /cards/{id}: deleta um card pelo seu id.
*/