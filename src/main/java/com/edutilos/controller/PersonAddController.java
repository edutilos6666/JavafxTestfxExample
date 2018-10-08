package com.edutilos.controller;

import com.edutilos.com.edutilos.dao.DAO;
import com.edutilos.com.edutilos.dao.PersonDAO;
import com.edutilos.model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.stream.Collectors;

public class PersonAddController {
    @FXML
    private TextArea areaOutput;
    @FXML
    private TextField fieldId, fieldName, fieldAge, fieldWage, fieldActive;

    private DAO<Person, Long> dao;

    public PersonAddController() {
        this.dao = new PersonDAO();
    }

    @FXML
    public void onEnter(ActionEvent evt) {
        TextField field = (TextField)evt.getTarget();
        areaOutput.setText(field.getText());
    }

    @FXML
    public void handleBtnAddClicked(ActionEvent evt) {
        try {
            long id = Long.parseLong(fieldId.getText());
            String name = fieldName.getText();
            int age = Integer.parseInt(fieldAge.getText());
            double wage = Double.parseDouble(fieldWage.getText());
            boolean active = Boolean.parseBoolean(fieldActive.getText());
            final String nl =  System.getProperty("line.separator");
            StringBuilder sb = new StringBuilder();
/*            sb.append("<<Person Details>>").append(nl)
                    .append("id = ").append(id).append(nl)
                    .append("name = ").append(name).append(nl)
                    .append("age = ").append(age).append(nl)
                    .append("wage = ").append(wage).append(nl)
                    .append("active = ").append(active);
            areaOutput.setText(sb.toString()); */
            dao.create(new Person(id, name, age, wage, active));
            areaOutput.setText(dao.findAll().stream().map(p-> p.toString()).collect(Collectors.joining("\n")));
        } catch(Exception ex) {
            ex.printStackTrace();
            areaOutput.setText(ex.getMessage());
        }
    }

    @FXML
    public void handleBtnClearClicked(ActionEvent evt) {
        fieldId.setText("");
        fieldName.setText("");
        fieldAge.setText("");
        fieldWage.setText("");
        fieldActive.setText("");
    }

    @FXML
    public void handleBtnCancelClicked(ActionEvent evt) {

    }

    @FXML
    public void handleBtnUpdateClicked(ActionEvent evt) {
        try {
            long id = Long.parseLong(fieldId.getText());
            String name = fieldName.getText();
            int age = Integer.parseInt(fieldAge.getText());
            double wage = Double.parseDouble(fieldWage.getText());
            boolean active = Boolean.parseBoolean(fieldActive.getText());
            dao.create(new Person(1L, "foobar", 10, 100.0,true));
            dao.save(1L, new Person(id, name, age, wage, active));
            areaOutput.setText(dao.findAll().stream().map(p-> p.toString()).collect(Collectors.joining("\n")));
        } catch(Exception ex) {
            ex.printStackTrace();
            areaOutput.setText(ex.getMessage());
        }
    }

    @FXML
    public void handleBtnDeleteClicked(ActionEvent evt) {
        try {
            long id = Long.parseLong(fieldId.getText());
            dao.create(new Person(1L, "foobar", 10, 100.0,true));
            dao.create(new Person(2L, "pako", 20, 200.0,false));
            dao.create(new Person(3L, "deko", 30, 300.0,true));
            dao.delete(id);
            areaOutput.setText(dao.findAll().stream().map(p-> p.toString()).collect(Collectors.joining("\n")));
        } catch(Exception ex) {
            ex.printStackTrace();
            areaOutput.setText(ex.getMessage());
        }
    }

    @FXML
    public void handleBtnFindClicked(ActionEvent evt) {
        try {
            long id = Long.parseLong(fieldId.getText());
            dao.create(new Person(id, "foo", 10, 100.0, true));
            areaOutput.setText(dao.find(id).toString());
        } catch(Exception ex) {
            ex.printStackTrace();
            areaOutput.setText(ex.getMessage());
        }
    }

    @FXML
    public void handleBtnFindAllClicked(ActionEvent evt) {
        try {
    /*        dao.create(new Person(1L, "foobar", 10, 100.0,true));
            dao.create(new Person(2L, "pako", 20, 200.0,false));
            dao.create(new Person(3L, "deko", 30, 300.0,true));*/
            areaOutput.setText(dao.findAll().stream().map(p-> p.toString()).collect(Collectors.joining("\n")));
        } catch(Exception ex) {
            ex.printStackTrace();
            areaOutput.setText(ex.getMessage());
        }
    }
}
