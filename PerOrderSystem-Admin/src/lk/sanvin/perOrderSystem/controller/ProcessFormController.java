/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.controller;

import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.sanvin.perOrderSystem.dto.CashierDTO;
import lk.sanvin.perOrderSystem.dto.CookDTO;
import lk.sanvin.perOrderSystem.dto.OrderDTO;
import lk.sanvin.perOrderSystem.dto.OrderDetailDTO;
import lk.sanvin.perOrderSystem.dto.TPODTO;
import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.observer.Subject;
import lk.sanvin.perOrderSystem.proxy.ProxyHandler;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.custom.CashierService;
import lk.sanvin.perOrderSystem.service.custom.CookService;
import lk.sanvin.perOrderSystem.service.custom.OrderDetailService;
import lk.sanvin.perOrderSystem.service.custom.OrderService;
import lk.sanvin.perOrderSystem.service.custom.TPOService;
import lk.sanvin.perOrderSystem.util.ObserverCollector;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class ProcessFormController implements Initializable, Observer {

    //Lits for Tables
    private ObservableList<OrderDTO> orderList = FXCollections.observableArrayList();
    private ObservableList<OrderDetailDTO> orderDetailList = FXCollections.observableArrayList();
    private ObservableList<TPODTO> tpoList = FXCollections.observableArrayList();
    private ObservableList<CookDTO> cookList = FXCollections.observableArrayList();
    private ObservableList<CashierDTO> cashierList = FXCollections.observableArrayList();
    //Services
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private TPOService tpoService;
    private CookService cookService;
    private CashierService cashierService;
    //Observer Subjects
    private Subject subOrder;
    private Subject subTpo;
    private Subject subCook;
    private Subject subCashier;

    private int oid;
    @FXML
    private TableView<OrderDTO> tblOrders;
    @FXML
    private TableColumn<OrderDTO, String> clmCustomerName;
    @FXML
    private TableColumn<OrderDTO, String> clmTelephoneNumber;
    @FXML
    private TableColumn<OrderDTO, String> clmAddress;
    @FXML
    private TableColumn<OrderDTO, String> clmDeliveryTime;
    @FXML
    private TableColumn<OrderDTO, String> clmTOP;
    @FXML
    private TableColumn<OrderDTO, String> clmCook;
    @FXML
    private TableColumn<OrderDTO, String> clmDeliver;
    @FXML
    private TableColumn<OrderDTO, String> clmStatus;
    @FXML
    private TableView<OrderDetailDTO> tblOrderDetails;
    @FXML
    private TableColumn<OrderDetailDTO, String> clmDescription;
    @FXML
    private TableColumn<OrderDetailDTO, Integer> clmQty;
    @FXML
    private TableColumn<OrderDetailDTO, Double> clmUnitPrice;
    @FXML
    private TableColumn<OrderDetailDTO, Double> ckmAmount;
    @FXML
    private TableView<TPODTO> tblTpo;
    @FXML
    private TableColumn<TPODTO, String> clmTpoName;
    @FXML
    private TableColumn<TPODTO, String> clmTpoTelepnoneNo;
    @FXML
    private TableColumn<TPODTO, String> clmTpoNic;
    @FXML
    private TableView<CookDTO> tblCook;
    @FXML
    private TableColumn<CookDTO, String> clmCookName;
    @FXML
    private TableColumn<CookDTO, String> clmCookTelephoneNo;
    @FXML
    private TableColumn<CookDTO, String> clmCookNic;
    @FXML
    private TextField txtTotalQty;
    @FXML
    private TextField txtTotalAmount;
    @FXML
    private TableColumn<OrderDTO, Integer> clmOid;
    @FXML
    private TableColumn<CashierDTO, String> clmCashierTelephoneNo;
    @FXML
    private TableColumn<CashierDTO, String> clmCashierNic;
    @FXML
    private TableView<CashierDTO> tblCashier;
    @FXML
    private TableColumn<CashierDTO, CashierDTO> clmCashierName;
    @FXML
    private TableColumn<OrderDTO, String> clmCashier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            orderService = (OrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
            orderDetailService = (OrderDetailService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER_DETAIL);
            tpoService = (TPOService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TPO);
            cookService = (CookService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.COOK);
            cashierService = (CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
            //Observer
            UnicastRemoteObject.exportObject(this, 0);
            //Order
            subOrder = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
            subOrder.regesterObserver(this);
            ObserverCollector.getInstance().setOrderSubject(subOrder, (Observer)this);
            //Tpo
            subTpo = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TPO);
            subTpo.regesterObserver(this);
            ObserverCollector.getInstance().setTpoSubject(subTpo, (Observer)this);
            //Cook
            subCook = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.COOK);
            subCook.regesterObserver(this);
            ObserverCollector.getInstance().setCookSubject(subCook, (Observer)this);
            //cashier
            subCashier = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
            subCashier.regesterObserver(this);
            ObserverCollector.getInstance().setCashierSubject(subCashier, (Observer)this);
        } catch (Exception ex) {
            Logger.getLogger(ProcessFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initCol();
        loadTable();
        tblGetItem();
        
    }

    private void initCol() {
        //Main Tabale
        clmOid.setCellValueFactory(new PropertyValueFactory<>("oid"));
        clmCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmTelephoneNumber.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmDeliveryTime.setCellValueFactory(new PropertyValueFactory<>("deliveryDateTime"));
        clmTOP.setCellValueFactory(new PropertyValueFactory<>("top"));
        clmCook.setCellValueFactory(new PropertyValueFactory<>("cook"));
        clmCashier.setCellValueFactory(new PropertyValueFactory<>("cashier"));
        clmDeliver.setCellValueFactory(new PropertyValueFactory<>("deliver"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        //orderDetail tabel
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("discription"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        ckmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        //TPO tabale
        clmTpoName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmTpoTelepnoneNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmTpoNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        //Cook tabale
        clmCookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmCookTelephoneNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmCookNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        //Cashier tabale
        clmCashierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmCashierTelephoneNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmCashierNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

    }

    private void loadTable() {
        try {
            //order Table
            orderList.clear();
            List<OrderDTO> all = orderService.findByDate(Date());
            for (OrderDTO o : all) {
                orderList.add(o);
            }
            tblOrders.getItems().setAll(orderList);

            //top tabel
            tpoList.clear();
            List<TPODTO> tpos = tpoService.finbyStatus("Online");
            for (TPODTO o : tpos) {
                tpoList.add(o);
            }
            tblTpo.getItems().setAll(tpoList);

            //Cook tabel
            cookList.clear();
            List<CookDTO> cooks = cookService.finbyStatus("Online");
            for (CookDTO o : cooks) {
                cookList.add(o);
            }
            tblCook.getItems().setAll(cookList);

            //Cashier tabel
            cashierList.clear();
            List<CashierDTO> cashiers = cashierService.finbyStatus("Online");
            for (CashierDTO o : cashiers) {
                cashierList.add(o);
            }
            tblCashier.getItems().setAll(cashierList);

        } catch (Exception ex) {
            Logger.getLogger(ProcessFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tblGetItem() {
        orderDetailList.clear();
        tblOrders.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    oid = tblOrders.getSelectionModel().getSelectedItem().getOid();
                    orderDetailList.clear();
                    List<OrderDetailDTO> odList = orderDetailService.findByOid(oid);
                    for (OrderDetailDTO dto : odList) {
                        orderDetailList.add(dto);
                    }
                    tblOrderDetails.getItems().setAll(orderDetailList);
                    getTotalQty();
                    getTotaAmount();
                } catch (Exception ex) {
                    Logger.getLogger(ProcessFormController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    private String Date() {
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd");
        return (sdf.format(d1));
    }

    private void getTotalQty() {
        int d = 0;
        for (OrderDetailDTO o : orderDetailList) {
            d += o.getQty();
        }
        txtTotalQty.setText(d + "");
    }

    private void getTotaAmount() {
        double d = 0;
        for (OrderDetailDTO o : orderDetailList) {
            d += o.getAmount();
        }
        txtTotalAmount.setText(d + "");
    }

    @Override
    public void updateObserver() throws Exception {
        loadTable();
    }

}
