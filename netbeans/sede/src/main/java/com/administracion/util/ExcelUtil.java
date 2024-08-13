/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Anlod
 */
public class ExcelUtil {

    public byte[] buildExcelDocument(List<?> items, String sheetName,String [] fieldsOrder) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();

        ByteArrayOutputStream wbBytes = new ByteArrayOutputStream();
        try {

            XSSFSheet sheet = workbook.createSheet(sheetName != null && !sheetName.isEmpty() ? sheetName : "report");

            JsonUtil jsonUtil = new JsonUtil();
            JsonArray jsonArray = jsonUtil.toJSON(jsonUtil.objectToString(items));

            
            if (jsonArray.size() > 0) {
                buildHeaderExcel(sheet, jsonArray.get(0).getAsJsonObject());
                int rowNum = 1;
                for (JsonElement element : jsonArray) {
                    JsonObject jsonObjectItem = element.getAsJsonObject();
                    //create the row data
                    XSSFRow row = sheet.createRow(rowNum++);
                    int indexCell = 0;
                    while ( indexCell < fieldsOrder.length ) {
                        String currentValue = "";
                        if (!jsonObjectItem.get(fieldsOrder[indexCell]).isJsonNull()) {
                            currentValue = jsonObjectItem.get(fieldsOrder[indexCell]).getAsString();
                        }
                        row.createCell(indexCell).setCellValue(currentValue);
                        indexCell++;
                    }

                }
            }

            workbook.write(wbBytes);
        } catch (IllegalArgumentException e) {
            System.out.println("Error buildExcelDocument " + e.getMessage());
        } finally {
            workbook.close();
            wbBytes.close();
        }


        return wbBytes.toByteArray();
    }


    private  void buildHeaderExcel(XSSFSheet sheet, JsonObject jsonObject) {
        XSSFRow header = sheet.createRow(0);
        //Map<String, String> attributes = new HashMap<>();
        //List<String> attrs = new ArrayList<>();
        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
        int i = 0;
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            //attributes.put(entry.getKey(), jsonObject.get(entry.getKey()).getAsString());
            //attrs.add(entry.getKey());
            header.createCell(i).setCellValue(entry.getKey());
            i++;
        }

    }

}
