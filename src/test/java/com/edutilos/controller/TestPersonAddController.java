package com.edutilos.controller;

import com.edutilos.model.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class TestPersonAddController extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        Parent mainNode = (Parent)FXMLLoader.load(getClass().getResource("/javafx/PersonAddView.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
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
    public void testFieldId() {
        doTest("#areaOutput", "#fieldId", "1");
    }

    @Test
    public void testFieldName() {
        doTest("#areaOutput", "#fieldName", "foobar");
    }

    @Test
    public void testFieldAge() {
        TextArea areaOutput = (TextArea)GuiTest.find("#areaOutput");
        clickOn("#fieldAge");
        write("28");
        press(KeyCode.ENTER);
        assertThat(areaOutput.getText(), is("28"));
    }

    @Test
    public void testFieldWage() {
        doTest("#areaOutput", "#fieldWage", "100.123");
    }

    @Test
    public void testFieldActive() {
        doTest("#areaOutput", "#fieldActive", "false");
    }

    private void doTest(String srcName, String targetName, String value) {
        TextArea areaOutput = (TextArea)GuiTest.find(srcName);
        clickOn(targetName);
        write(value);
        press(KeyCode.ENTER);
        assertThat(areaOutput.getText(), is(value));
    }

    @Test
    public void testBtnAddClicked() {
        TextArea areaOutput = (TextArea)GuiTest.find("#areaOutput");
        TextField fieldId = (TextField)GuiTest.find("#fieldId");
        TextField fieldName = (TextField)GuiTest.find("#fieldName");
        TextField fieldAge = (TextField)GuiTest.find("#fieldAge");
        TextField fieldWage = (TextField)GuiTest.find("#fieldWage");
        TextField fieldActive = (TextField)GuiTest.find("#fieldActive");
        clickOn(fieldId);
        write("1");
        clickOn(fieldName);
        write("foobar");
        clickOn(fieldAge);
        write("10");
        clickOn(fieldWage);
        write("100.0");
        clickOn(fieldActive);
        write("true");
        clickOn("#btnAdd");
        final String nl =  System.getProperty("line.separator");
    /*    StringBuilder sb = new StringBuilder();
        sb.append("<<Person Details>>").append(nl)
                .append("id = ").append(1).append(nl)
                .append("name = ").append("foobar").append(nl)
                .append("age = ").append(10).append(nl)
                .append("wage = ").append(100.0).append(nl)
                .append("active = ").append(true);*/
        assertThat(areaOutput.getText(), is(Stream.of(new Person(1, "foobar", 10, 100.0, true)).map(p-> p.toString()).collect(Collectors.joining("\n"))));
    }


    @Test
    public void testBtnClearClicked() {
        TextArea areaOutput = (TextArea)GuiTest.find("#areaOutput");
        TextField fieldId = (TextField)GuiTest.find("#fieldId");
        TextField fieldName = (TextField)GuiTest.find("#fieldName");
        TextField fieldAge = (TextField)GuiTest.find("#fieldAge");
        TextField fieldWage = (TextField)GuiTest.find("#fieldWage");
        TextField fieldActive = (TextField)GuiTest.find("#fieldActive");
        clickOn("#btnClear");
        assertThat("", is(areaOutput.getText()));
        assertThat("", is(fieldId.getText()));
        assertThat("", is(fieldName.getText()));
        assertThat("", is(fieldAge.getText()));
        assertThat("", is(fieldWage.getText()));
        assertThat("", is(fieldActive.getText()));
    }

    @Test
    public void testBtnUpdateClicked() {
        TextArea areaOutput = (TextArea)GuiTest.find("#areaOutput");
        TextField fieldId = (TextField)GuiTest.find("#fieldId");
        TextField fieldName = (TextField)GuiTest.find("#fieldName");
        TextField fieldAge = (TextField)GuiTest.find("#fieldAge");
        TextField fieldWage = (TextField)GuiTest.find("#fieldWage");
        TextField fieldActive = (TextField)GuiTest.find("#fieldActive");
        clickOn(fieldId);
        write("1");
        clickOn(fieldName);
        write("pako");
        clickOn(fieldAge);
        write("20");
        clickOn(fieldWage);
        write("200.0");
        clickOn(fieldActive);
        write("false");
        clickOn("#btnUpdate");
        assertThat(areaOutput.getText(), is(Stream.of(new Person(1, "pako", 20, 200.0, false)).map(p-> p.toString()).collect(Collectors.joining("\n"))));
    }



    @Test
    public void testBtnDeleteClicked() {
        TextArea areaOutput = (TextArea)GuiTest.find("#areaOutput");
        TextField fieldId = (TextField)GuiTest.find("#fieldId");
        clickOn(fieldId);
        write("1");
        clickOn("#btnDelete");
        assertThat(areaOutput.getText(), is(Stream.of(new Person(2L, "pako", 20, 200.0,false),
                new Person(3L, "deko", 30, 300.0,true)).map(p-> p.toString()).collect(Collectors.joining("\n"))));
    }

    @Test
    public void testBtnFindClicked() {
        TextArea areaOutput = (TextArea)GuiTest.find("#areaOutput");
        TextField fieldId = (TextField)GuiTest.find("#fieldId");
        clickOn(fieldId);
        write("123");
        clickOn("#btnFind");
        assertThat(areaOutput.getText(), is(new Person(123L, "foo", 10, 100.0, true).toString()));
    }

    @Test
    public void testBtnFindAllClicked() {
        TextArea areaOutput = (TextArea)GuiTest.find("#areaOutput");
        TextField fieldId = (TextField)GuiTest.find("#fieldId");
        TextField fieldName = (TextField)GuiTest.find("#fieldName");
        TextField fieldAge = (TextField)GuiTest.find("#fieldAge");
        TextField fieldWage = (TextField)GuiTest.find("#fieldWage");
        TextField fieldActive = (TextField)GuiTest.find("#fieldActive");
        clickOn(fieldId);
        write("1");
        clickOn(fieldName);
        write("foobar");
        clickOn(fieldAge);
        write("10");
        clickOn(fieldWage);
        write("100.0");
        clickOn(fieldActive);
        write("true");
        clickOn("#btnAdd");
        fieldId.setText("");
        fieldName.setText("");
        fieldAge.setText("");
        fieldWage.setText("");
        fieldActive.setText("");

        clickOn(fieldId);
        write("2");
        clickOn(fieldName);
        write("pako");
        clickOn(fieldAge);
        write("20");
        clickOn(fieldWage);
        write("200.0");
        clickOn(fieldActive);
        write("false");
        clickOn("#btnAdd");
        fieldId.setText("");
        fieldName.setText("");
        fieldAge.setText("");
        fieldWage.setText("");
        fieldActive.setText("");

        clickOn(fieldId);
        write("3");
        clickOn(fieldName);
        write("deko");
        clickOn(fieldAge);
        write("30");
        clickOn(fieldWage);
        write("300.0");
        clickOn(fieldActive);
        write("true");
        clickOn("#btnAdd");
        fieldId.setText("");
        fieldName.setText("");
        fieldAge.setText("");
        fieldWage.setText("");
        fieldActive.setText("");


        clickOn("#btnFindAll");
        assertThat(areaOutput.getText(), is(Stream.of(new Person(1L, "foobar", 10, 100.0,true),
                new Person(2L, "pako", 20, 200.0,false),
                new Person(3L, "deko", 30, 300.0,true)).map(Person::toString).collect(Collectors.joining("\n"))));
    }





}
