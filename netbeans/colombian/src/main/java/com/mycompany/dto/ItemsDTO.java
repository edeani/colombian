/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

/**
 *
 * @author edeani
 */
public class ItemsDTO {
    private Long id;
    private String label;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String Label) {
        this.label = Label;
    }
    
    
}
