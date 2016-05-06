/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.controllers.util;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.util.Callback;


/**
 *
 * @author EderArmando
 */
public class ComboBoxEditingCell extends TableCell<Class, Typ> {

        private ComboBox<Typ> comboBox;
        private ObservableList<Typ> typData;
        
        public ComboBoxEditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createComboBox();
                setText(null);
                setGraphic(getComboBox());
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(getTyp().getTyp());
            setGraphic(null);
        }

        @Override
        public void updateItem(Typ item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (getComboBox() != null) {
                        getComboBox().setValue(getTyp());
                    }
                    setText(getTyp().getTyp());
                    setGraphic(getComboBox());
                } else {
                    setText(getTyp().getTyp());
                    setGraphic(null);
                }
            }
        }

        private void createComboBox() {
            setComboBox(new ComboBox<>(getTypData()));
            comboBoxConverter(getComboBox());
            getComboBox().valueProperty().set(getTyp());
            getComboBox().setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            getComboBox().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                           System.out.println("Committed: " + getComboBox().getSelectionModel().getSelectedItem());
                            commitEdit(getComboBox().getSelectionModel().getSelectedItem());
                        }
                    });
            /*comboBox.setOnAction((e) -> {
                System.out.println("Committed: " + comboBox.getSelectionModel().getSelectedItem());
                commitEdit(comboBox.getSelectionModel().getSelectedItem());
            });*/
//            comboBox.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//                if (!newValue) {
//                    commitEdit(comboBox.getSelectionModel().getSelectedItem());
//                }
//            });
        }

        private void comboBoxConverter(ComboBox<Typ> comboBox) {
            // Define rendering of the list of values in ComboBox drop down. 
            Callback<ListView<Typ>,ListCell<Typ>> cbComboBox = new Callback<ListView<Typ>, ListCell<Typ>>() {
                @Override
                public ListCell<Typ> call(ListView<Typ> param) {
                    return new ListCell<Typ>(){
                        @Override
                        protected void updateItem(Typ item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item == null || empty) {
                                setText(null);
                            } else {
                                setText(item.getTyp());
                            }
                        }
                    };
                }
            };
            
            comboBox.setCellFactory(cbComboBox);
        }

        private Typ getTyp() {
            return getItem() == null ? new Typ("") : getItem();
        }

    /**
     * @return the typData
     */
    public ObservableList<Typ> getTypData() {
        return typData;
    }

    /**
     * @param typData the typData to set
     */
    public void setTypData(ObservableList<Typ> typData) {
        this.typData = typData;
    }

    /**
     * @return the comboBox
     */
    public ComboBox<Typ> getComboBox() {
        return comboBox;
    }

    /**
     * @param comboBox the comboBox to set
     */
    public void setComboBox(ComboBox<Typ> comboBox) {
        this.comboBox = comboBox;
    }
        
        
    }

