package com.ProjectSD.repository;

import com.ProjectSD.model.Usuario;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {

private final MongoTemplate mongoTemplate;
private final MongoClient mongoClient;
private final MongoDatabase database;
private final MongoCollection<Document> collection;
private String collectionName = "usuarios";

@Autowired
public UsuarioRepository(MongoTemplate mongoTemplate) {
    this.mongoClient = MongoClients.create("mongodb://localhost:27017");
    this.database = mongoClient.getDatabase("perfil");
    this.collection = database.getCollection(collectionName);
    this.mongoTemplate = mongoTemplate;
}

    public List<Document> findAll() {
        return collection.find().into(new ArrayList<>());
    }

    public Document findByEmail(String email) {
        Document query = new Document("email", email);
        Document usuarioDoc = collection.find(query).first();

        if (usuarioDoc != null) {
            // Converte o documento em um objeto Usuario           
            return usuarioDoc;
        }

        return null; // Usuário não encontrado
    }

    public void save(Document usuario) {
        collection.insertOne(usuario);
    }

    public void update(Usuario usuario) {
        mongoTemplate.save(usuario, "usuarios");
    }

    public void delete(Usuario usuario) {
        mongoTemplate.remove(usuario, "usuarios");
    }
    
    
        public Integer getLastUserId() {
        var sort = new Document("_id", -1); // Ordenar pelo _id em ordem decrescente
        var query = new Document().append("id", new Document("$exists", true)); // Encontrar documentos com o campo "id_card"
        var projection = new Document("_id", 0).append("id", 2); // Retornar apenas o campo "id"
        Integer lastCardId = null;
        try {
                // Executar a query
        var cursor = collection.find(query)
                .projection(projection)
                .sort(sort)
                .limit(1)
                .iterator();

        // Obter o resultado

        if (cursor.hasNext()) {
            var doc = cursor.next();
            lastCardId = doc.getInteger("id")+1;
        }
        } catch (Exception e) {
            System.out.println("Erro no getLastUserID: "+ e);
        }
        return lastCardId;
    }
}
