/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjectSD.service;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import org.json.JSONException;
import org.springframework.stereotype.Component;
@Component
public class JsonConverter {

    public static String convertListToJson(List<Document> documents) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Document document : documents) {
            JSONObject jsonObject = new JSONObject(document.toJson());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
}
