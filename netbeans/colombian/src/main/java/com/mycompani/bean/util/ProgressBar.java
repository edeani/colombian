/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompani.bean.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author joseefren
 */
@ManagedBean(name="progressBar")
@RequestScoped
public class ProgressBar implements Serializable{

    private Integer progress;
    private Integer sizeNum;
    /**
     * Creates a new instance of ProgressBar
     */
    public ProgressBar() {
    }

    /**
     * @return the progress
     */
    public Integer getProgress() {
        return progress;
    }

    /**
     * @param progress the progress to set
     */
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    /**
     * @return the sizeNum
     */
    public Integer getSizeNum() {
        return sizeNum;
    }

    /**
     * @param sizeNum the sizeNum to set
     */
    public void setSizeNum(Integer sizeNum) {
        this.sizeNum = sizeNum;
    }
    
    
    public int factorNum(){
        int num =1;
        if(sizeNum>100 && sizeNum<=1000){
            num=10;
        }
        if(sizeNum>1000 && sizeNum<=10000){
            num=100;
        }
        if(sizeNum>10000){
            num=1000;
        }
        return num;
    }
    
    public void excuteProgress(){
        int Factor=factorNum();
        for (int i = 0; i < sizeNum; i++) {
            progress=i/Factor;
        //codigo
        }
        progress=100;
    }
    
    public void onComplete(){
    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Login Succesfull","full"));
    }
    
    public void cancel(){
     progress=0;
     sizeNum=0;
    }
}
