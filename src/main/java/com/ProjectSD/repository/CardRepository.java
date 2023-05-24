package com.ProjectSD.repository;

import com.ProjectSD.model.Card;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Sorts;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepository {
    

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;
    private final MongoTemplate mongoTemplate;
    private String collectionName = "englishCards";

    public CardRepository(MongoTemplate mongoTemplate) {
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("FlashCards");
        this.collection = database.getCollection(collectionName);
        this.mongoTemplate = mongoTemplate;
    }
    
    public List<Document> findAllByUserId(int id) {
        System.out.println("id em repository: "+ id);
        Document query = new Document("id_user", id);
        List<Document> results = collection.find(query).into(new ArrayList<>());  
        return results;
    }
    /*
    public Document findAllByTime(int id) {
        System.out.println("id em repository: " + id);
        Document query = new Document("id_user", id);

        Document results = collection.aggregate(List.of(
                Aggregates.match(query),
                Aggregates.sort(new Document("_id.date", 1))
        )).first();
        System.out.println("Imprimindo date: "+ results.getDate("_id.date"));
        

        return results;
    }*/
    public Document findAllByTime(int id) {
    System.out.println("id em repository: " + id);
    Document query = new Document("id_user", id);

    List<Document> results = collection.aggregate(Arrays.asList(
            Aggregates.match(query),
            Aggregates.sort(Sorts.descending("time"))
    )).into(new ArrayList<>());
    
    if (!results.isEmpty()) {
        System.out.println("Imprimindo date: " + results.get(0).getDate("time"));
    }

    return results.isEmpty() ? null : results.get(0);
}


    public Document findById(String id) {
        return collection.find(new Document("_id", id)).first();
    }
    
    public Document findByIdCard(String id_card) {
 
        return collection.find(new Document("id_card", id_card)).first();
    }
        
    public Document findByEngName(String engName) {
        
        return collection.find(new Document("engName", engName)).first();
    }

    public List<Document> findByPortName(String portName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("portName").is(portName));
        return mongoTemplate.find(query, Document.class, collectionName);
    }
    
    public void save(Document card) {
        collection.insertOne(card);
    }
    
    public void update(String id, Document card) {
        collection.replaceOne(new Document("id", id), card);
    }
    
    public void delete(int id) {
        collection.deleteOne(new Document("id", id));
    }
    
    /*
    CLASSE DE TESTE PAR TIMER
    */
    
    public void timerCard(){
        findAllByUserId(1);
        
    }
    /*FIM DA CLASSE TESTE*/
    public Integer getLastCardId() {
        var sort = new Document("_id", -1); // Ordenar pelo _id em ordem decrescente
        var query = new Document().append("id_card", new Document("$exists", true)); // Encontrar documentos com o campo "id_card"
        var projection = new Document("_id", 0).append("id_card", 2); // Retornar apenas o campo "id_card"
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
            lastCardId = doc.getInteger("id_card")+1;
        }
        } catch (Exception e) {
            System.out.println("Erro no getLastCardID: "+ e);
        }
        return lastCardId;
    }




}
