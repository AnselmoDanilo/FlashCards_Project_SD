package com.ProjectSD.controller;

import com.ProjectSD.model.Usuario;
import com.ProjectSD.repository.UsuarioRepository;
import com.ProjectSD.service.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.bson.Document;
import org.json.JSONException;
import static org.springframework.data.mongodb.core.aggregation.MergeOperation.UniqueMergeId.id;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
     public static int id_USER;
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private JsonConverter jsonConverter;

    @PostMapping
    public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario) {
        // Verificar se o email já está em uso
        System.out.println("Usuario: "+ usuario.toString());
        if (repository.findByEmail(usuario.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já está em uso");
        }
        Document doc = new Document();
        doc.append("id", repository.getLastUserId())
                .append("nome", usuario.getNome())
                .append("sobrenome", usuario.getSobrenome())
                .append("senha", usuario.getSenha())
                .append("email", usuario.getEmail());
        // Adicionar o usuário à lista
        repository.save(doc);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
    }

    @GetMapping
    public String findAll() throws JSONException {
        List<Document> usuarios = repository.findAll();
        String json = jsonConverter.convertListToJson(usuarios);
        return json;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Document> obterUsuario(@PathVariable String email) {
        // Procurar o usuário pelo email
        Document usuario = repository.findByEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }

        // Usuário não encontrado
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String senha) {
        // Verificar se o email e senha correspondem a um usuário válido
        Document usuario = repository.findByEmail(email);
        System.out.println("Usuario: " + usuario.toString());

        if (usuario != null && senha.equals(usuario.getString("senha"))) {
            this.id_USER = usuario.getInteger("id");
            System.out.println("Valor de idUser em login : "+ id_USER);
            return ResponseEntity.ok("Login realizado com sucesso");
        }

        // Credenciais inválidas
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
    
    

}
