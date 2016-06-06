/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.controllers.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author EderArmando
 */
 public class Typ {

        private final SimpleStringProperty typ;

        public Typ(String typ) {
            this.typ = new SimpleStringProperty(typ);
        }

        public String getTyp() {
            return this.typ.get();
        }

        public StringProperty typProperty() {
            return this.typ;
        }

        public void setTyp(String typ) {
            this.typ.set(typ);
        }

        @Override
        public String toString() {
            return typ.get();
        }

    }
