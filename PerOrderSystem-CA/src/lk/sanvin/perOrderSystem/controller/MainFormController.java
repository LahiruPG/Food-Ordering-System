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
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.sanvin.perOrderSystem.dto.CashierDTO;
import lk.sanvin.perOrderSystem.proxy.ProxyHandler;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.custom.CashierService;
import lk.sanvin.perOrderSystem.service.custom.DeliverService;
import lk.sanvin.perOrderSystem.util.DropBox;
import lk.sanvin.perOrderSystem.util.ObserverCollector;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class MainFormController implements Initializable {

    private CashierDTO dto;
    private DeliverService deliverService;
    private CashierService cashierService;
    //Time
    private int minute;
    private int second;
    private int hour;
    @FXML
    private StackPane rootPane;
    @FXML
    private JFXButton btnExit;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton btnOrders;
    @FXML
    private JFXButton btnLogOut;

    private FXMLLoader viewOrderForm;
    private Pane viewOrderPane;
    @FXML
    private JFXDrawer drawerMainBar;
    @FXML
    private VBox hBoxSideBar;
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
    private Label lblInvalidPasswordOrNic;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXTextField txtUserNic;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        deliverService =(DeliverService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVER);
        cashierService = (CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
        viewOrderForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/ViewOrderForm.fxml"));
        viewOrderPane = (Pane) viewOrderForm.load();
        } catch (IOException ex) {
        Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
        Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        drawerMainBar.setSidePane(hBoxSideBar);
        borderPane.setCenter(viewOrderPane);
        
       login();
       setDate();
       setTime();
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

    @FXML
    private void btnOrdersAction(ActionEvent event) {
        borderPane.setCenter(viewOrderPane);
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
                    try {
                        logOut();
                        login();
                    } catch (Exception ex) {
                        Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

    private void mainBar() {
        //   viewOrderPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{});
        if (viewOrderPane.getAccessibleText() == "sideBar") {
            drawerMainBar.close();
            System.out.println("Close");
        }

    }

    @FXML
    private void txtLoginNicMouseClick(javafx.scene.input.MouseEvent event) {
        lblInvalidPasswordOrNic.setVisible(false);
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
        txtLoginNic.setText("");
        txtLoginPassword.setText("");

        txtUserName.setText("");
        txtUserNic.setText("");

        rootPane.setDisable(true);
        pneLogin.setVisible(true);

        BoxBlur bb = new BoxBlur();
        bb.setWidth(15);
        bb.setHeight(15);
        bb.setIterations(10);
        rootPane.setEffect(bb);

    }
    private void logOut(){
        try {
            drawerMainBar.close();
            dto.setStatus("Offline");
            cashierService.update(dto);
            ObserverCollector.getInstance().unregesterAllObservers();
        } catch (Exception ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void logIn(){
        dto = new CashierDTO();
        try {
            List<CashierDTO> list = cashierService.finbyNicAndPassword(txtLoginNic.getText(), txtLoginPassword.getText());
            System.out.println("list ---------"+list);
            for (CashierDTO o : list) {
                dto = o;
            }
            if (dto.getNic() != null) {
                DropBox.getInstance().setCashierdto(dto);
                txtUserName.setText(dto.getName());
                txtUserNic.setText(dto.getNic());
                pneLogin.setVisible(false);
                rootPane.setEffect(null);
                rootPane.setDisable(false);
                drawerMainBar.open();
                dto.setStatus("Online");
                cashierService.update(dto);
            } else {
                lblInvalidPasswordOrNic.setVisible(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void txtLoginNicAction(ActionEvent event) {
        
         txtLoginPassword.requestFocus();
    }

    @FXML
    private void txtLoginPasswordAction(ActionEvent event) {
        logIn();
    }
    private void setTime(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {            
        Calendar cal = Calendar.getInstance();
        second = cal.get(Calendar.SECOND);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);
        //System.out.println(hour + ":" + (minute) + ":" + second);
        lblTime.setText(hour + ":" + (minute)+ ":" + second);
    }),
         new KeyFrame(Duration.seconds(1))
    );
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();
    }
    public void setDate() {
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd");
       lblDate.setText(sdf.format(d1));
    }
}
