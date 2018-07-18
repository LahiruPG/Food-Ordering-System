/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class DashboardFormController implements Initializable {
    private double xx=0,yy=0;
    @FXML
    private NumberAxis y;
    @FXML
    private NumberAxis x;
    @FXML
    private JFXButton btnUp;
    @FXML
    private StackedAreaChart<?, ?> chart;
    XYChart.Series series = new XYChart.Series();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadChart();
    }

    @FXML
    private void btnUpAction(ActionEvent event) {
        
        series.getData().add(new XYChart.Data(xx, yy));
        xx+=10;
        yy+=20;
        
        System.out.println(x+" - "+y);
       
    }

    private void loadChart() {
        series.setName("Orders");
        series.getData().add(new XYChart.Data(1, 23));
        chart.getData().addAll(series);
    }
}
