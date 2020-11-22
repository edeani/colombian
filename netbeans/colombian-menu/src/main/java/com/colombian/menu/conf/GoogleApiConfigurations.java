/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author 10 Spring Creators
 */
@Component
@ConfigurationProperties(prefix = "sheet")
public class GoogleApiConfigurations {
    
    private String idColombianmenu;
    private String categorySheet;
    private String productSheet;
    private String summarySheet;
    
    private String categoryRange;
    private String productRange;
    private String summaryRange;
    
    @Value("${google.file-app}")
    private String jsonConfApi;
    
    @Value("${google.port-app}")
    private Integer portApp;

    public String getIdColombianmenu() {
        return idColombianmenu;
    }

    public void setIdColombianmenu(String idColombianmenu) {
        this.idColombianmenu = idColombianmenu;
    }

    public String getCategorySheet() {
        return categorySheet;
    }

    public void setCategorySheet(String categorySheet) {
        this.categorySheet = categorySheet;
    }

    public String getProductSheet() {
        return productSheet;
    }

    public void setProductSheet(String productSheet) {
        this.productSheet = productSheet;
    }

    public String getSummarySheet() {
        return summarySheet;
    }

    public void setSummarySheet(String summarySheet) {
        this.summarySheet = summarySheet;
    }

    public String getCategoryRange() {
        return categoryRange;
    }

    public void setCategoryRange(String categoryRange) {
        this.categoryRange = categoryRange;
    }

    public String getProductRange() {
        return productRange;
    }

    public void setProductRange(String productRange) {
        this.productRange = productRange;
    }

    public String getSummaryRange() {
        return summaryRange;
    }

    public void setSummaryRange(String summaryRange) {
        this.summaryRange = summaryRange;
    }

    public String getJsonConfApi() {
        return jsonConfApi;
    }

    public void setJsonConfApi(String jsonConfApi) {
        this.jsonConfApi = jsonConfApi;
    }

    public Integer getPortApp() {
        return portApp;
    }

    public void setPortApp(Integer portApp) {
        this.portApp = portApp;
    }
    
    
    
}
