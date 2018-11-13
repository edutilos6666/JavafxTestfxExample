package com.edutilos.controller;

import com.edutilos.dao.WorkerDAO;
import com.edutilos.model.Worker;
import com.edutilos.util.CustomDialogs;
import com.edutilos.util.CustomValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Created by edutilos on 13.11.18.
 */
public class WorkerCreateController {
    @FXML
    private Label lblTitle, lblId, lblName, lblAge, lblWage, lblActive,
            lblIdError, lblNameError, lblAgeError, lblWageError, lblActiveError;
    @FXML
    private TextField fieldId, fieldName, fieldAge, fieldWage, fieldActive;
    @FXML
    private Button btnOk, btnClear, btnCancel;
    private WorkerDAO workerDAO;

    @FXML
    public void initialize() {
        initComponents();
        registerEvents();
        batchValidate();
    }

    private void initComponents() {
        lblIdError.setText("");
        lblNameError.setText("");
        lblAgeError.setText("");
        lblWageError.setText("");
        lblActiveError.setText("");
    }
    private void batchValidate() {
  /*      fieldId.clear();
        fieldId.textProperty().setValue("");
        fieldName.clear();
        fieldAge.clear();
        fieldWage.clear();
        fieldActive.clear();*/
        lblIdError.setText(CustomValidator.validateLong(fieldId));
        lblNameError.setText(CustomValidator.validateString(fieldName));
        lblAgeError.setText(CustomValidator.validateInt(fieldAge));
        lblWageError.setText(CustomValidator.validateDouble(fieldWage));
        lblActiveError.setText(CustomValidator.validateBoolean(fieldActive));
    }

    public void setWorkerDAO(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }

    private void registerEvents() {
        btnOk.setOnAction(evt-> {
            try {
                btnOk.getScene().setCursor(Cursor.WAIT);
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        long id = Long.parseLong(fieldId.getText());
                        String name = fieldName.getText();
                        int age = Integer.parseInt(fieldAge.getText());
                        double wage = Double.parseDouble(fieldWage.getText());
                        boolean active = Boolean.parseBoolean(fieldActive.getText());
                        workerDAO.create(new Worker(id, name, age, wage,active));
                        return null;
                    }
                };
                task.setOnSucceeded(evt2-> {
                    btnOk.getScene().setCursor(Cursor.DEFAULT);
                    ((Stage)btnOk.getScene().getWindow()).close();
                });
                new Thread(task).start();
            } catch(Exception ex) {
                CustomDialogs.showInfoWarningErrorDialog("Error", ex.getMessage());
            }
        });

        btnClear.setOnAction(evt-> {
            fieldId.clear();
            fieldName.clear();
            fieldAge.clear();
            fieldWage.clear();
            fieldActive.clear();
            lblIdError.setText("");
            lblNameError.setText("");
            lblAgeError.setText("");
            lblWageError.setText("");
            lblActiveError.setText("");
            batchValidate();
        });

        btnCancel.setOnAction(evt-> {
            ((Stage)btnCancel.getScene().getWindow()).close();
        });

        fieldId.setOnKeyPressed(evt-> {
            if(evt.isShiftDown() && evt.getCode().equals(KeyCode.ENTER)) {
                fieldId.fireEvent(mimicKeyPressForTextField(evt, fieldId, KeyCode.TAB, true));
            } else if(evt.getCode().equals(KeyCode.ENTER)) {
                fieldId.fireEvent(mimicKeyPressForTextField(evt, fieldId, KeyCode.TAB, false));
            }
        });

        fieldName.setOnKeyPressed(evt-> {
            if(evt.isShiftDown() && evt.getCode().equals(KeyCode.ENTER)) {
                fieldName.fireEvent(mimicKeyPressForTextField(evt, fieldName, KeyCode.TAB, true));
            } else if(evt.getCode().equals(KeyCode.ENTER)) {
                fieldName.fireEvent(mimicKeyPressForTextField(evt, fieldName, KeyCode.TAB, false));
            }
        });

        fieldAge.setOnKeyPressed(evt-> {
            if(evt.isShiftDown() && evt.getCode().equals(KeyCode.ENTER)) {
                fieldAge.fireEvent(mimicKeyPressForTextField(evt, fieldAge, KeyCode.TAB, true));
            } else if(evt.getCode().equals(KeyCode.ENTER)) {
                fieldAge.fireEvent(mimicKeyPressForTextField(evt, fieldAge, KeyCode.TAB, false));
            }
        });

        fieldWage.setOnKeyPressed(evt-> {
            if(evt.isShiftDown() && evt.getCode().equals(KeyCode.ENTER)) {
                fieldWage.fireEvent(mimicKeyPressForTextField(evt, fieldWage, KeyCode.TAB, true));
            } else if(evt.getCode().equals(KeyCode.ENTER)) {
                fieldWage.fireEvent(mimicKeyPressForTextField(evt, fieldWage, KeyCode.TAB, false));
            }
        });

        fieldActive.setOnKeyPressed(evt-> {
            if(evt.isShiftDown() && evt.getCode().equals(KeyCode.ENTER)) {
                fieldActive.fireEvent(mimicKeyPressForTextField(evt, fieldActive, KeyCode.TAB, true));
            } else if(evt.getCode().equals(KeyCode.ENTER)) {
                fieldActive.fireEvent(mimicKeyPressForTextField(evt, fieldActive, KeyCode.TAB, false));
            }
        });

        btnOk.setOnKeyPressed(evt-> {
            if(evt.isShiftDown() && evt.getCode().equals(KeyCode.ENTER)) {
                btnOk.fireEvent(mimicKeyPressForTextField(evt, btnOk, KeyCode.TAB, true));
            } else if(evt.isControlDown() && evt.getCode().equals(KeyCode.ENTER)) {
                btnOk.fireEvent(mimicKeyPressForTextField(evt, btnOk, KeyCode.TAB, false));
            } else if(evt.getCode().equals(KeyCode.ENTER)) {
                btnOk.fire();
            }
        });

        btnClear.setOnKeyPressed(evt-> {
            if(evt.isShiftDown() && evt.getCode().equals(KeyCode.ENTER)) {
                btnClear.fireEvent(mimicKeyPressForTextField(evt, btnClear, KeyCode.TAB, true));
            } else if(evt.isControlDown() && evt.getCode().equals(KeyCode.ENTER)) {
                btnClear.fireEvent(mimicKeyPressForTextField(evt, btnClear, KeyCode.TAB, false));
            } else if(evt.getCode().equals(KeyCode.ENTER)) {
                btnClear.fire();
            }
        });


        btnCancel.setOnKeyPressed(evt-> {
            if(evt.isShiftDown() && evt.getCode().equals(KeyCode.ENTER)) {
                btnCancel.fireEvent(mimicKeyPressForTextField(evt, btnCancel, KeyCode.TAB, true));
            } else if(evt.isControlDown() && evt.getCode().equals(KeyCode.ENTER)) {
                btnCancel.fireEvent(mimicKeyPressForTextField(evt, btnCancel, KeyCode.TAB, false));
            } else if(evt.getCode().equals(KeyCode.ENTER)) {
                btnCancel.fire();
            }
        });


        fieldId.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lblIdError.setText(CustomValidator.validateLong(newValue));
            }
        });
        fieldName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lblNameError.setText(CustomValidator.validateString(newValue));
            }
        });
        fieldAge.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lblAgeError.setText(CustomValidator.validateInt(newValue));
            }
        });
        fieldWage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lblWageError.setText(CustomValidator.validateDouble(newValue));
            }
        });
        fieldActive.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lblActiveError.setText(CustomValidator.validateBoolean(newValue));
            }
        });


        btnOk.disableProperty().bind(
                lblIdError.textProperty().isNotEmpty()
                .or(lblNameError.textProperty().isNotEmpty())
                .or(lblAgeError.textProperty().isNotEmpty())
                .or(lblWageError.textProperty().isNotEmpty())
                .or(lblActiveError.textProperty().isNotEmpty())
        );

        btnClear.disableProperty().bind(
                fieldId.textProperty().isEmpty()
                .and(fieldName.textProperty().isEmpty())
                .and(fieldAge.textProperty().isEmpty())
                .and(fieldWage.textProperty().isEmpty())
                .and(fieldActive.textProperty().isEmpty())
//                .and(lblIdError.textProperty().isEmpty())
//                .and(lblNameError.textProperty().isEmpty())
//                .and(lblAgeError.textProperty().isEmpty())
//                .and(lblWageError.textProperty().isEmpty())
//                .and(lblActiveError.textProperty().isEmpty())
        );
    }

    private KeyEvent mimicKeyPressForTextField(KeyEvent keyEvent, Node node, KeyCode keyCode, boolean shiftDown) {
        KeyEvent ret = new KeyEvent(keyEvent, node, KeyEvent.KEY_PRESSED, "", "", keyCode, shiftDown, false, false, false);
        return ret;
    }
}
