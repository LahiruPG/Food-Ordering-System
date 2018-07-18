/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LahiruPG
 */
public class StartUp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/sanvin/perOrderSystem/view/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Kitchen Application");
        primaryStage.setFullScreen(true);
        primaryStage.setOnCloseRequest(e -> {
            primaryStage.setFullScreen(true);
            e.consume();
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
