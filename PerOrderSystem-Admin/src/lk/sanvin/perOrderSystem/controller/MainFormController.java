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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.sanvin.perOrderSystem.dto.AdminDTO;
import lk.sanvin.perOrderSystem.dto.CashierDTO;
import lk.sanvin.perOrderSystem.proxy.ProxyHandler;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.custom.AdminService;
import lk.sanvin.perOrderSystem.util.DropBox;
import lk.sanvin.perOrderSystem.util.ObserverCollector;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class MainFormController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXButton btnExit;
    @FXML
    private BorderPane borderPane;

    private FXMLLoader TPOForm;
    private FXMLLoader cookForm;
    private FXMLLoader dashboardForm;
    private FXMLLoader itemForm;
    private FXMLLoader processForm;
    private FXMLLoader adminForm;
    private FXMLLoader cashierForm;
    private FXMLLoader deliverForm;
    private FXMLLoader ordersForm;

    private Pane TPOPane;
    private Pane cookPane;
    private Pane dashboardPane;
    private Pane itemPane;
    private Pane processPane;
    private Pane adminPane;
    private Pane cashierPane;
    private Pane deliverPane;
    private Pane ordersPane;

    private AdminService adminService;
    private AdminDTO dto;

    @FXML
    private JFXDrawer dwrMainButtons;
    @FXML
    private JFXDrawer dweManageEmployees;
    @FXML
    private JFXButton btnManageEmployees;
    @FXML
    private VBox vboxManageEmployees;
    @FXML
    private JFXButton btnTelophoneOperators;
    @FXML
    private JFXButton btnCooks;
    @FXML
    private JFXButton btnDilivers;
    @FXML
    private JFXButton btnDashboard;
    @FXML
    private VBox vboxMainButtons;
    @FXML
    private JFXButton btnManageItems;
    @FXML
    private JFXButton btnProcess;
    @FXML
    private JFXButton btnAdmin;
    @FXML
    private JFXButton btnCAshier;
    @FXML
    private JFXButton btnOrders;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            adminService = (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        } catch (Exception ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        vboxMainButtons.setVisible(false);
        dwrMainButtons.close();
        loadForms();
        dweManageEmployees.close();
        vboxManageEmployees.setVisible(false);
        dwrMainButtons.setSidePane(vboxMainButtons);
        vboxMainButtons.setVisible(true);
        dwrMainButtons.open();
        borderPane.setCenter(dashboardPane);
        login();
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
                ObserverCollector.getInstance().unregesterAllObservers();
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

    private void loadForms() {
        try {
            TPOForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/TPOForm.fxml"));
            TPOPane = (Pane) TPOForm.load();
            cookForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/CookForm.fxml"));
            cookPane = (Pane) cookForm.load();

            dashboardForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/DashboardForm.fxml"));
            dashboardPane = (Pane) dashboardForm.load();
            itemForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/ItemForm.fxml"));
            itemPane = (Pane) itemForm.load();
            processForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/ProcessForm.fxml"));
            processPane = (Pane) processForm.load();

            adminForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/AdminForm.fxml"));
            adminPane = (Pane) adminForm.load();

            cashierForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/CashierForm.fxml"));
            cashierPane = (Pane) cashierForm.load();

            deliverForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/DeliverForm.fxml"));
            deliverPane = (Pane) deliverForm.load();

            ordersForm = new FXMLLoader(getClass().getResource("/lk/sanvin/perOrderSystem/view/ViewOrderForm.fxml"));
            ordersPane = (Pane) ordersForm.load();

        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnManageEmployeesAction(ActionEvent event) {
        dweManageEmployees.setSidePane(vboxManageEmployees);
        vboxManageEmployees.setVisible(true);
        borderPane.setCenter(TPOPane);
        dweManageEmployees.open();

    }

    @FXML
    private void btnTelophoneOperatorsAction(ActionEvent event) {

        borderPane.setCenter(TPOPane);
    }

    @FXML
    private void btnCooksAction(ActionEvent event) {

        borderPane.setCenter(cookPane);
    }

    @FXML
    private void btnDiliversAction(ActionEvent event) {
        borderPane.setCenter(deliverPane);
    }

    @FXML
    private void btnDashboardAction(ActionEvent event) {
        borderPane.setCenter(dashboardPane);
    }

    @FXML
    private void btnManageItemsAction(ActionEvent event) {
        dweManageEmployees.close();
        borderPane.setCenter(itemPane);
    }

    @FXML
    private void btnProcessAction(ActionEvent event) {
        dweManageEmployees.close();
        borderPane.setCenter(processPane);
    }

    @FXML
    private void btnAdminAction(ActionEvent event) {
        borderPane.setCenter(adminPane);
    }

    @FXML
    private void btnCAshierAction(ActionEvent event) {
        borderPane.setCenter(cashierPane);
    }

    @FXML
    private void btnOrdersAction(ActionEvent event) {
        borderPane.setCenter(ordersPane);

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

    @FXML
    private void btbLoginAction(ActionEvent event) {
         logIn();
        
    }

    @FXML
    private void btnLoginExitAction(ActionEvent event) {
        System.exit(0);
    }

    private void logIn() {
        dto = new AdminDTO();
        try {
            List<AdminDTO> list = adminService.finbyNicAndPassword(txtLoginNic.getText(), txtLoginPassword.getText());
            System.out.println("list ---------" + list);
            for (AdminDTO o : list) {
                dto = o;
            }
            if (dto.getNic() != null) {
                //DropBox.getInstance().setCashierdto(dto);
                txtUserName.setText(dto.getName());
                txtUserNic.setText(dto.getNic());
                pneLogin.setVisible(false);
                rootPane.setEffect(null);
                rootPane.setDisable(false);
                dwrMainButtons.open();
                // dto.setStatus("Online");
                // adminService.update(dto);
            } else {
                lblInvalidPasswordOrNic.setVisible(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void logOut() {
        try {
            dwrMainButtons.close();
            dto.setStatus("Offline");
            adminService.update(dto);
            ObserverCollector.getInstance().unregesterAllObservers();
        } catch (Exception ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        dwrMainButtons.close();
    }

}
