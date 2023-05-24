/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjectSD.model;


import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
public class Card {
    
    @Id
    private String id;
    private String userId;
    private String engName;
    private String engDesc;
    private String portName;
    private String portDesc;
    private int nota;
    private Date time;

  

    // construtor padrão e construtor com parâmetros
    public Card() {}

    public Card(String userId, String engName, String engDesc, String portName, String portDesc, int nota) {
        this.userId = userId;
        this.engName = engName;
        this.engDesc = engDesc;
        this.portName = portName;
        this.portDesc = portDesc;
        this.nota = nota;
        this.time = time;
    }
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    // getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getEngDesc() {
        return engDesc;
    }

    public void setEngDesc(String engDesc) {
        this.engDesc = engDesc;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getPortDesc() {
        return portDesc;
    }

    public void setPortDesc(String portDesc) {
        this.portDesc = portDesc;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    // toString
    @Override
    public String toString() {
        return "Card [id=" + id + ", userId=" + userId + ", engName=" + engName + ", engDesc=" + engDesc
                + ", portName=" + portName + ", portDesc=" + portDesc + ", nota=" + nota + "]";
    }
}
