/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjectSD.service;

import com.ProjectSD.model.Card;
import com.ProjectSD.repository.CardRepository;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
     @Autowired
     private final CardRepository cardRepository;
    
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    /*
      public Integer generateNewCardId() {
        Integer lastCardId = cardRepository.getLastCardId();
        Integer newCardId = lastCardId + 1;
         System.out.println("Ultimo id:  >>>>>"+ newCardId);
        return newCardId;
    }
    */
    public List<Document> findAll() {
        return cardRepository.findAll();
    }

    public Document findById(String id) {
        return cardRepository.findById(id);
    }

    public Document findByEngName(String engName) {
        return cardRepository.findByEngName(engName);
    }

    public List<Document> findByPortName(String portName) {
        return cardRepository.findByPortName(portName);
    }

    public void save(Document card) {
        cardRepository.save(card);
    }

    public void update(String id, Document card) {
        cardRepository.update(id, card);
    }

    public void delete(String id) {
        cardRepository.delete(id);
    }

    
    public int translateNota(String nota){
        int num = 1;
        switch(nota){
            case "Esqueci Totalmente": num=1;
            break;
            case "Lembrei pouco": num=2;
            break;
            case "Quase acertei": num=3;
            break;
            case "Acertei pouco": num=4;
            break;
            case "Acertei Totalmente": num=5;
            break;
        }
        return num;
    }

}
/*
dificuldade para lembrar

1- Esqueci Totalmente - Muito Fraco
2- Lembrei pouco      - Fraco
3- Quase acertei      - Medio
4- Acertei pouco      - Forte
5- Acertei Totalmente - Muito Forte

*/