/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.sanvin.perOrderSystem.dto.TPODTO;
import lk.sanvin.perOrderSystem.proxy.ProxyHandler;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.custom.TPOService;
import lk.sanvin.perOrderSystem.util.DropBox;
import lk.sanvin.perOrderSystem.util.ObserverCollector;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class MainFormController implements Initializable {

    TPOService topService;
    TPODTO dto;
    @FXML
    private StackPane rootPane;
    @FXML
    private Button btnPlaseOrder;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton btnExit;

    FXMLLoader addOrderForm;
    FXMLLoader editOrderForm;

    Pane orderPane;
    Pane editOrderPane;
    //Time
    private int minute;
    private int second;
    private int hour;
    @FXML
    private JFXButton btnCheckOrder;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private AnchorPane pneLogin;
    @FXML
    private JFXTextField txtLoginNic;
    @FXML
    private JFXPasswordField txtLoginPassword;
    @FXML
    private JFXButton btbLogin;
    @FXML
    private JFXButton btnLoginExit;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox vbox;
    @FXML
    private Label lblInvalidPasswordOrNic;
    @FXML
    private JFXTextField txtUserName1;
    @FXML
    private JFXTextField txtUserNic1;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        login();
        loadForms();
        drawer.setSidePane(vbox);

    }

    private void loadForms() {
        try {
            topService = (TPOService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TPO);
            addOrderForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/PlaseOrderForm.fxml"));
            orderPane = (Pane) addOrderForm.load();
            addOrderForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/EditOrderForm.fxml"));
            editOrderPane = (Pane) addOrderForm.load();

        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTime();
        setDate();
    }

    @FXML
    private void btnPlaseOrderAction(ActionEvent event) throws IOException {
        borderPane.setCenter(orderPane);
    }

    @FXML
    private void btnExitAction(ActionEvent event) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Do you want to exit"));

        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        JFXButton btnYes = new JFXButton("   Yes   ");

        btnYes.setStyle("-fx-background-color: #039be5; -fx-text-fill: white;");
        JFXButton btnNo = new JFXButton("   No   ");
        btnNo.setStyle("-fx-background-color: #039be5; -fx-text-fill: white;");
        btnYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logOut();
                System.exit(0);
            }
        });
        btnNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        dialogLayout.setActions(btnYes, btnNo);

        dialog.show();
    }

    /**/
    @FXML
    private void btnCheckOrderAction(ActionEvent event) {

        borderPane.setCenter(editOrderPane);
    }

    @FXML
    private void btnLogOutAction(ActionEvent event) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Do you want to Log out"));

        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        JFXButton btnYes = new JFXButton("   Yes   ");

        btnYes.setStyle("-fx-background-color: #039be5; -fx-text-fill: white;");
        JFXButton btnNo = new JFXButton("   No   ");
        btnNo.setStyle("-fx-background-color: #039be5; -fx-text-fill: white;");
        btnYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    logOut();
                    login();
                    dialog.close();
                } catch (Exception ex) {
                    Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        dialogLayout.setActions(btnYes, btnNo);

        dialog.show();

    }

    @FXML
    private void btbLoginAction(ActionEvent event) {
        logIn();

    }

    @FXML
    private void btnLoginExitAction(ActionEvent event) {
        System.exit(0);
    }

    private void login() {
        txtUserName1.setText("");
        txtUserNic1.setText("");
        txtLoginNic.setText("");
        txtLoginPassword.setText("");
        rootPane.setDisable(true);
        pneLogin.setVisible(true);

        BoxBlur bb = new BoxBlur();
        bb.setWidth(15);
        bb.setHeight(15);
        bb.setIterations(10);
        rootPane.setEffect(bb);

    }

    private void logOut() {
        try {
            drawer.close();
            dto.setStatus("Offline");
            System.out.println(dto);
            topService.update(dto);
            ObserverCollector.getInstance().unregesterAllObservers();
        } catch (Exception ex) {
            System.out.println("Log out TPO");
        }
    }

    private void logIn() {
        dto = new TPODTO();
        try {
            List<TPODTO> list = topService.finbyNicAndPassword(txtLoginNic.getText(), txtLoginPassword.getText());
            for (TPODTO o : list) {
                dto = o;
            }
            System.out.println(dto);

            if (dto.getNic() != null) {
                txtUserName1.setText(dto.getName());
                txtUserNic1.setText(dto.getNic());
                pneLogin.setVisible(false);
                rootPane.setEffect(null);
                rootPane.setDisable(false);
                drawer.open();
                dto.setStatus("Online");
                topService.update(dto);
                DropBox.getInstance().setTpodto(dto);
            } else {
                lblInvalidPasswordOrNic.setVisible(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void txtLoginNicMouseClick(MouseEvent event) {
        lblInvalidPasswordOrNic.setVisible(false);
    }

    @FXML
    private void txtLoginNicAction(ActionEvent event) {
        txtLoginPassword.requestFocus();
    }

    @FXML
    private void txtLoginPasswordAction(ActionEvent event) {
        logIn();
    }

    private void setTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            hour = cal.get(Calendar.HOUR);
            //hour=cal.get(Calendar.)
            //System.out.println(hour + ":" + (minute) + ":" + second);
            lblTime.setText(hour + ":" + (minute) + ":" + second);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void setDate() {
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd");
        lblDate.setText(sdf.format(d1));
    }

    
}
