/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.domain.enums;

/**
 *
 * @author Eslayfer
 */
public enum EntityStatus { 
	
	A {
		public String toString() {
			return "Activo";
		}
	},

	I {
		public String toString() {
			return "Inactivo";
		}
	};
}

