/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.conf;

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
    
    
    
}
