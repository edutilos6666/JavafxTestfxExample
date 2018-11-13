package com.edutilos.controller;


import com.edutilos.dao.WorkerDAO;
import com.edutilos.model.Worker;
import com.edutilos.util.CustomDialogs;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.List;
import java.util.concurrent.TimeoutException;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestWorkerCreateController extends ApplicationTest {
    private WorkerDAO workerDAO;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/WorkerMainWindow.fxml"));
        try {
            AnchorPane root = loader.load();
            WorkerMainController workerMainController = loader.getController();
            workerDAO = new WorkerDAO();
            workerMainController.setData(workerDAO);
            workerMainController.initialize();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(false);
            primaryStage.show();
            primaryStage.toFront();
            primaryStage.setOnCloseRequest(evt-> {
                workerDAO.closeFactory();
            });
        } catch(Exception ex) {
            ex.printStackTrace();
            CustomDialogs.showInfoWarningErrorDialog("Error", ex.getMessage());
        }
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void test_A_CreateWorkerOk() {
        Button btnCreate = (Button)GuiTest.find("#btnCreate");
        clickOn(btnCreate);
        WaitForAsyncUtils.waitForFxEvents();
/*
        WaitForAsyncUtils.waitForAsyncFx(5000, ()-> {

        });
*/
        TextField fieldId, fieldName, fieldAge, fieldWage, fieldActive;
        Button btnOk, btnClear, btnCancel;
        fieldId = (TextField)GuiTest.find("#fieldId");
        fieldName = (TextField)GuiTest.find("#fieldName");
        fieldAge = (TextField)GuiTest.find("#fieldAge");
        fieldWage = (TextField)GuiTest.find("#fieldWage");
        fieldActive = (TextField)GuiTest.find("#fieldActive");
        btnOk = (Button)GuiTest.find("#btnOk");
        btnClear = (Button)GuiTest.find("#btnClear");
        btnCancel = (Button)GuiTest.find("#btnCancel");
        clickOn(fieldId);
        write("5");
        clickOn(fieldName);
        write("foobar");
        clickOn(fieldAge);
        write("50");
        clickOn(fieldWage);
        write("500.0");
        clickOn(fieldActive);
        write("true");
        clickOn(btnOk);
//        sleep(2000);
        List<Worker> all = workerDAO.findAll();
        assertThat(all.size(), is(5));


    }

    @Test
    public void test_B_CreateWorkerClear() {
        Button btnCreate = (Button)GuiTest.find("#btnCreate");
        clickOn(btnCreate);
        WaitForAsyncUtils.waitForFxEvents();
        TextField fieldId, fieldName, fieldAge, fieldWage, fieldActive;
        Button btnOk, btnClear, btnCancel;
        fieldId = (TextField)GuiTest.find("#fieldId");
        fieldName = (TextField)GuiTest.find("#fieldName");
        fieldAge = (TextField)GuiTest.find("#fieldAge");
        fieldWage = (TextField)GuiTest.find("#fieldWage");
        fieldActive = (TextField)GuiTest.find("#fieldActive");
        btnClear = (Button)GuiTest.find("#btnClear");
        btnCancel = (Button)GuiTest.find("#btnCancel");
        clickOn(fieldId);
        write("5");
        clickOn(fieldName);
        write("foobar");
        clickOn(fieldAge);
        write("50");
        clickOn(fieldWage);
        write("500.0");
        clickOn(fieldActive);
        write("true");
        clickOn(btnClear);
        assertThat(fieldId.getText(), is(""));
        assertThat(fieldName.getText(), is(""));
        assertThat(fieldAge.getText(), is(""));
        assertThat(fieldWage.getText(), is(""));
        assertThat(fieldActive.getText(), is(""));
        clickOn(btnCancel);


    }


    @Test
    public void test_C_CreateWorkerCancel() {
        Button btnCreate = (Button)GuiTest.find("#btnCreate");
        clickOn(btnCreate);
        WaitForAsyncUtils.waitForFxEvents();
        TextField fieldId, fieldName, fieldAge, fieldWage, fieldActive;
        Button btnOk, btnClear, btnCancel;
        fieldId = (TextField)GuiTest.find("#fieldId");
        fieldName = (TextField)GuiTest.find("#fieldName");
        fieldAge = (TextField)GuiTest.find("#fieldAge");
        fieldWage = (TextField)GuiTest.find("#fieldWage");
        fieldActive = (TextField)GuiTest.find("#fieldActive");
        btnClear = (Button)GuiTest.find("#btnClear");
        btnCancel = (Button)GuiTest.find("#btnCancel");
        clickOn(fieldId);
        write("5");
        clickOn(fieldName);
        write("foobar");
        clickOn(fieldAge);
        write("50");
        clickOn(fieldWage);
        write("500.0");
        clickOn(fieldActive);
        write("true");
        clickOn(btnCancel);
        assertThat(btnCancel.getScene().getWindow().isShowing(), is(false));
    }
}
