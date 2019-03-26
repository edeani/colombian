package com.printing.apps.enumeration;

public enum StateEnum {
	Pendiente("N"),//no procesada
	Progreso("P"),//en progreso
    Error("E"),//Error
    Procesada("I");//Impresa
	
	private final String estado;
	
	private StateEnum(String estado) {   
        this.estado=estado;
    }
	
	public String getEstado(){
        return estado;
    }
}
