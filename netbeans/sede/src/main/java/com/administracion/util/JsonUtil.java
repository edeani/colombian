/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.List;

/**
 *
 * @author Anlod
 */
public class JsonUtil {

    public String objectToString(List<?> vmlist) {
        // Create an instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Convert the object to JSON string
        String jsonString = "{}";
        try {
            jsonString = objectMapper.writeValueAsString(vmlist);
        } catch (JsonProcessingException e) {
            System.out.println("Error objectToString "+e.getMessage());
        }
        // Print the JSON string
        return jsonString;
    }

    public JsonArray toJSON(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonElement tradeElement = parser.parse(jsonString);
        return tradeElement.getAsJsonArray();
    }

}
