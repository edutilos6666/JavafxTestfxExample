package com.edutilos.main;

import com.edutilos.controller.WorkerMainController;
import com.edutilos.dao.WorkerDAO;
import com.edutilos.util.CustomDialogs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class WorkerRunner extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    private WorkerDAO workerDAO;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/WorkerMainWindow.fxml"));
        try {
            AnchorPane root = loader.load();
            WorkerMainController workerMainController = loader.getController();
            workerDAO = new WorkerDAO();
            workerMainController.setData(workerDAO);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
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

    public WorkerDAO getWorkerDAO() {
        return this.workerDAO;
    }
}
