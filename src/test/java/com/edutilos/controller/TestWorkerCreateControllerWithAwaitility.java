package com.edutilos.controller;


import com.edutilos.dao.WorkerDAO;
import com.edutilos.main.WorkerRunner;
import com.edutilos.model.Worker;
import com.edutilos.util.CustomDialogs;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.awaitility.Awaitility;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.util.WaitForAsyncUtils;

import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.testfx.api.FxAssert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestWorkerCreateControllerWithAwaitility extends ApplicationTest {
    private WorkerDAO workerDAO;
  /*  @Override
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
    }*/

    @Before
    public void setUp() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(WorkerRunner.class);
        workerDAO = new WorkerDAO();
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
//        Awaitility.await().until(()-> lookup("#fieldId").query() != null);
        WaitForAsyncUtils.waitForFxEvents();
        WaitForAsyncUtils.asyncFx(()-> {
            return lookup("#lblTitle").query() != null &&
                    ((Label)lookup("#lblTitle").query()).getText().equalsIgnoreCase("Create New Worker")
//                    && ((Label)lookup("#lblTitle").query()).getScene().getWindow().
                    ;
        });
//        WaitForAsyncUtils.waitForFxEvents();
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
        assertThat(all.size(), is(1));


    }

    @Test
    public void test_B_CreateWorkerClear() {
        Button btnCreate = (Button)GuiTest.find("#btnCreate");
        clickOn(btnCreate);
//        WaitForAsyncUtils.waitForFxEvents();
//        Awaitility.await().until(()-> lookup("#fieldId").query() != null);
        WaitForAsyncUtils.waitForFxEvents();
        WaitForAsyncUtils.asyncFx(()-> {
            return lookup("#lblTitle").query() != null &&
                    ((Label)lookup("#lblTitle").query()).getText().equalsIgnoreCase("Create New Worker")
//                    && ((Label)lookup("#lblTitle").query()).getScene().getWindow().
                    ;
        });
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
//        WaitForAsyncUtils.waitForFxEvents();
//        Awaitility.await().until(()-> lookup("#fieldId").query() != null);
        WaitForAsyncUtils.waitForFxEvents();
        WaitForAsyncUtils.asyncFx(()-> {
            return lookup("#lblTitle").query() != null &&
                    ((Label)lookup("#lblTitle").query()).getText().equalsIgnoreCase("Create New Worker")
//                    && ((Label)lookup("#lblTitle").query()).getScene().getWindow().
                    ;
        });
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

    @Test
    public void test_D_createWorkerOkWithKeyPressed() {
        Button btnCreate = lookup("#btnCreate").query();
        clickOn(btnCreate);
        WaitForAsyncUtils.waitForFxEvents();
        WaitForAsyncUtils.asyncFx(()-> {
            return lookup("#lblTitle").query() != null &&
                    ((Label)lookup("#lblTitle").query()).getText().equalsIgnoreCase("Create New Worker")
//                    && ((Label)lookup("#lblTitle").query()).getScene().getWindow().
                    ;
        });
//        Awaitility.await().until(()-> lookup("#lblTitle").query() != null &&
//                ((Label)lookup("#lblTitle").query()).getText().equalsIgnoreCase("Create New Worker"));
        TextField fieldId, fieldName, fieldAge, fieldWage, fieldActive;
        Button btnOk, btnClear, btnCancel;
        fieldId = lookup("#fieldId").query();
        fieldName = lookup("#fieldName").query();
        fieldAge = lookup("#fieldAge").query();
        fieldWage = lookup("#fieldWage").query();
        fieldActive = lookup("#fieldActive").query();
        btnOk = lookup("#btnOk").query();
        btnClear = lookup("#btnClear").query();
        btnCancel = lookup("#btnCancel").query();
        Label lblTitle, lblId, lblName, lblAge, lblWage, lblActive;
        Label lblIdError, lblNameError, lblAgeError, lblWageError, lblActiveError;
        lblTitle = lookup("#lblTitle").query();
        lblId = lookup("#lblId").query();
        lblName = lookup("#lblName").query();
        lblAge = lookup("#lblAge").query();
        lblWage = lookup("#lblWage").query();
        lblActive = lookup("#lblActive").query();
        lblIdError = lookup("#lblIdError").query();
        lblNameError = lookup("#lblNameError").query();
        lblAgeError = lookup("#lblAgeError").query();
        lblWageError = lookup("#lblWageError").query();
        lblActiveError = lookup("#lblActiveError").query();

        verifyThat(lblTitle, NodeMatchers.isNotNull());
        verifyThat(lblId, NodeMatchers.isNotNull());
        verifyThat(fieldId, NodeMatchers.isNotNull());
        verifyThat(lblIdError, NodeMatchers.isNotNull());
        verifyThat(lblName, NodeMatchers.isNotNull());
        verifyThat(fieldName, NodeMatchers.isNotNull());
        verifyThat(lblNameError, NodeMatchers.isNotNull());
        verifyThat(lblAge, NodeMatchers.isNotNull());
        verifyThat(fieldAge, NodeMatchers.isNotNull());
        verifyThat(lblAgeError, NodeMatchers.isNotNull());
        verifyThat(lblWage, NodeMatchers.isNotNull());
        verifyThat(fieldWage, NodeMatchers.isNotNull());
        verifyThat(lblWageError, NodeMatchers.isNotNull());
        verifyThat(lblActive, NodeMatchers.isNotNull());
        verifyThat(fieldActive, NodeMatchers.isNotNull());
        verifyThat(lblActiveError, NodeMatchers.isNotNull());

        verifyThat(lblTitle.getText(), is("Create New Worker"));
        verifyThat(lblId.getText(), is("Id:"));
        verifyThat(lblName.getText(), is("Name:"));
        verifyThat(lblAge.getText(), is("Age:"));
        verifyThat(lblWage.getText(), is("Wage:"));
        verifyThat(lblActive.getText(), is("Active:"));
        verifyThat(btnOk.getText(), is("OK"));
        verifyThat(btnClear.getText(), is("Clear"));
        verifyThat(btnCancel.getText(), is("Cancel"));
        verifyThat(fieldId.getText(), is(""));
        verifyThat(fieldName.getText(), is(""));
        verifyThat(fieldAge.getText(), is(""));
        verifyThat(fieldWage.getText(), is(""));
        verifyThat(fieldActive.getText(), is(""));

        //go to fieldId
        clickOn(fieldId);
        write("foo").push(KeyCode.ENTER)
                .write("").push(KeyCode.ENTER)
                .write("bar").push(KeyCode.ENTER)
                .write("bim").push(KeyCode.ENTER)
                .write("pako").push(KeyCode.ENTER);

        verifyThat(lblIdError.getText(), not(""));
        verifyThat(lblNameError.getText(), not(""));
        verifyThat(lblAgeError.getText(), not(""));
        verifyThat(lblWageError.getText(), not(""));
        verifyThat(lblActiveError.getText(), not(""));

//        sleep(5000);
        //go to btnClear
//        press(KeyCode.CONTROL, KeyCode.ENTER);
        //press btnClear
        press(KeyCode.ENTER);
        verifyThat(fieldId.getText(), is(""));
        verifyThat(fieldName.getText(), is(""));
        verifyThat(fieldAge.getText(), is(""));
        verifyThat(fieldWage.getText(), is(""));
        verifyThat(fieldActive.getText(), is(""));

        //go to fieldId
//        press(KeyCode.CONTROL, KeyCode.ENTER);
//        press(KeyCode.CONTROL, KeyCode.ENTER);

        clickOn(fieldId)
                .write("6").push(KeyCode.ENTER).push(KeyCode.ENTER)
                .write("foobar").push(KeyCode.ENTER)
                .write("60").push(KeyCode.ENTER)
                .write("600.0").push(KeyCode.ENTER)
                .write("true").push(KeyCode.ENTER);
    /*    clickOn(fieldId);
        write("6"); //.push(KeyCode.ENTER)
        clickOn(fieldName);
        write("foobar"); //.push(KeyCode.ENTER)
        clickOn(fieldAge);
        write("60"); //.push(KeyCode.ENTER)
        clickOn(fieldWage);
        write("600.0"); //.push(KeyCode.ENTER)
        clickOn(fieldActive);
        write("true"); //.push(KeyCode.ENTER);*/


        verifyThat(lblIdError.getText(), is(""));
        verifyThat(lblNameError.getText(), is(""));
        verifyThat(lblAgeError.getText(), is(""));
        verifyThat(lblWageError.getText(), is(""));
        verifyThat(lblActiveError.getText(), is(""));
        //press btnOk
//        push(KeyCode.ENTER);
        clickOn(btnOk);
//        sleep(3000);
        List<Worker> all = workerDAO.findAll();
        assertThat(all.size(), is(1));
    }
}
