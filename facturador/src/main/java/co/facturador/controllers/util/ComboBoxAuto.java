/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.controllers.util;

/**
 *
 * @author EderArmando
 */
import co.facturador.dto.SelectItemDto;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class ComboBoxAuto<T> implements EventHandler<KeyEvent> {

    private ComboBox comboBox;
    private StringBuilder sb;
    private ObservableList<T> data;
    private boolean moveCaretToPos = false;
    private int caretPos;
    private String tipo;

    public ComboBoxAuto(final ComboBox comboBox, String tipo) {
        this.comboBox = comboBox;
        this.tipo = tipo;
        sb = new StringBuilder();
        data = comboBox.getItems();

        this.comboBox.setEditable(true);
        this.comboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                comboBox.hide();
            }
        });

        this.comboBox.setOnKeyReleased(ComboBoxAuto.this);

        if (this.tipo.equals("numero")) {
            this.comboBox.setConverter(new StringConverter() {
                private Map<String, Object> map = new HashMap<>();

                @Override
                public String toString(Object item) {
                    // JOptionPane.showConfirmDialog(null, "Aja");
                    if (item != null) {
                        SelectItemDto itemSelect = (SelectItemDto) item;
                        String str = itemSelect.getId().toString();
                        map.put(str, item);
                        return str;
                    } else {
                        return "";
                    }
                }

                @Override
                public Object fromString(String string) {
                    if (!map.containsKey(string)) {
                        comboBox.setValue(null);
                        comboBox.getEditor().clear();
                        return null;
                    }
                    return map.get(string);
                }

            });
        } else if (this.tipo.equals("cadena")) {
            this.comboBox.setConverter(new StringConverter() {
                private Map<String, Object> map = new HashMap<>();

                @Override
                public String toString(Object item) {
                    // JOptionPane.showConfirmDialog(null, "Aja");
                    if (item != null) {
                        SelectItemDto itemSelect = (SelectItemDto) item;
                        String str = itemSelect.getTexto();
                        map.put(str, item);
                        return str;
                    } else {
                        return "";
                    }
                }

                @Override
                public Object fromString(String string) {
                    if (!map.containsKey(string)) {
                        comboBox.setValue(null);
                        comboBox.getEditor().clear();
                        return null;
                    }
                    return map.get(string);
                }

            });

        }
    }

    @Override
    public void handle(KeyEvent event) {
        
        if (event.getCode() == KeyCode.UP) {
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length());
            return;
        } else if (event.getCode() == KeyCode.DOWN) {
            if (!comboBox.isShowing()) {
                comboBox.show();
            }
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length());
            return;
        } else if (event.getCode() == KeyCode.BACK_SPACE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        } else if (event.getCode() == KeyCode.DELETE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        }

        if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.isControlDown() || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
            return;
        }

        ObservableList list = FXCollections.observableArrayList();
        for (int i = 0; i < data.size(); i++) {
            SelectItemDto item = (SelectItemDto) data.get(i);
            //Debe quedar en combinacion alfanumerica para que los encuentre
             String nombre = item.getId() + "-" + item.getTexto();
            if (nombre.toLowerCase().contains(
                    ComboBoxAuto.this.comboBox
                    .getEditor().getText().toLowerCase())) {
                list.add(item);
            }else if(ComboBoxAuto.this.comboBox
                    .getEditor().getText().toLowerCase().equals("")){
                list.addAll(data);
                break;
            }
        }
        
        String t = comboBox.getEditor().getText();

        comboBox.setItems(list);
        comboBox.getEditor().setText(t);
        if (!moveCaretToPos) {
            caretPos = -1;
        }
        moveCaret(t.length());
        if (!list.isEmpty()) {
            comboBox.show();
        }
    }

    private void moveCaret(int textLength) {
        if (caretPos == -1) {
            comboBox.getEditor().positionCaret(textLength);
        } else {
            comboBox.getEditor().positionCaret(caretPos);
        }
        moveCaretToPos = false;
    }
}
