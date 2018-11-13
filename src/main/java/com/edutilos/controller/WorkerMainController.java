package com.edutilos.controller;

import com.edutilos.dao.WorkerDAO;
import com.edutilos.model.Worker;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;


public class WorkerMainController {
    @FXML
    private Label lblTitle;
    @FXML
    private TableView<Worker> tvWorkers;
    @FXML
    private TableColumn<Worker,Long> colId;
    @FXML
    private TableColumn<Worker,String> colName;
    @FXML
    private TableColumn<Worker,Integer> colAge;
    @FXML
    private TableColumn<Worker, Double> colWage;
    @FXML
    private TableColumn<Worker, Boolean> colActive;
    @FXML
    private Button btnCreate;
    private WorkerDAO workerDAO;

    @FXML
    public void initialize() {

    }

    public void setData(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
        initComponents();
        addMockData();
        registerEvents();
    }

    private void initComponents() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colWage.setCellValueFactory(new PropertyValueFactory<>("wage"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));
    }
    private void registerEvents() {
        btnCreate.setOnAction(evt-> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/WorkerCreateWindow.fxml"));
            try {
                AnchorPane root = loader.load();
                WorkerCreateController workerCreateController = loader.getController();
                workerCreateController.setWorkerDAO(workerDAO);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.showAndWait();
                refreshTableView();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void refreshTableView() {
        tvWorkers.getScene().setCursor(Cursor.WAIT);
        Task<List<Worker>> task = new Task<List<Worker>>() {
            @Override
            protected List<Worker> call() throws Exception {
                return workerDAO.findAll();
            }
        };
        task.setOnSucceeded(evt-> {
            tvWorkers.setItems(FXCollections.observableArrayList(task.getValue()));
            tvWorkers.getScene().setCursor(Cursor.DEFAULT);
        });

        new Thread(task).start();
    }

    private void addMockData() {
        tvWorkers.setCursor(Cursor.WAIT);
        Task<List<Worker>> task = new Task<List<Worker>>() {
            @Override
            protected List<Worker> call() throws Exception {
                workerDAO.create(new Worker(1, "foo", 10, 100.0, true));
                workerDAO.create(new Worker(2, "bar", 20, 200.0, false));
                workerDAO.create(new Worker(3, "bim", 30, 300.0, true));
                workerDAO.create(new Worker(4, "pako", 40, 400.0, false));
                return workerDAO.findAll();
            }
        };
        task.setOnSucceeded(evt-> {
            tvWorkers.getItems().addAll(task.getValue());
            tvWorkers.setCursor(Cursor.DEFAULT);
        });

        new Thread(task).start();
    }
}
