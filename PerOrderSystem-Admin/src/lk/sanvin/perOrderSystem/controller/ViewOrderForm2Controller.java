/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import lk.sanvin.perOrderSystem.dto.CashierDTO;
import lk.sanvin.perOrderSystem.dto.DeliverDTO;
import lk.sanvin.perOrderSystem.dto.OrderDTO;
import lk.sanvin.perOrderSystem.dto.OrderDetailDTO;
import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.observer.Subject;
import lk.sanvin.perOrderSystem.proxy.ProxyHandler;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.custom.CashierService;
import lk.sanvin.perOrderSystem.service.custom.DeliverService;
import lk.sanvin.perOrderSystem.service.custom.OrderDetailService;
import lk.sanvin.perOrderSystem.service.custom.OrderService;
import lk.sanvin.perOrderSystem.util.DropBox;
import lk.sanvin.perOrderSystem.util.Notifi;
import lk.sanvin.perOrderSystem.util.ObserverCollector;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class ViewOrderForm2Controller implements Initializable ,Observer{

    //lists for tables
    private ObservableList<OrderDTO> orderList = FXCollections.observableArrayList();
    private ObservableList<OrderDetailDTO> orderDetailList = FXCollections.observableArrayList();
    private ObservableList<String> statusList = FXCollections.observableArrayList();
    private ObservableList<String> deliverList = FXCollections.observableArrayList();
    //Servises
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private DeliverService deliverService;
    private CashierService cashierService;

    private int oid;
    private OrderDTO orderDTO;

    //Time
    private int minute;
    //private int second;
    private int hour;

    private String date;

    @FXML
    private TableView<OrderDTO> tblOrders;
    @FXML
    private TableColumn<OrderDTO, String> clmOid;
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
    private TableColumn<OrderDTO, String> clmCashier;
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
    private TextField txtTotalQty;
    @FXML
    private TextField txtTotalAmount;
    @FXML
    private JFXTextField txtSerch;
    @FXML
    private JFXComboBox<String> combSetDeliver;
    @FXML
    private JFXTextField txtDeliverTelephoneNumber;
    @FXML
    private JFXTextField txtDeliverNIC;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancleAction;
    @FXML
    private JFXTextField txtOid;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtCustomerTelephoneNo;
    @FXML
    private TextField txtTime;
    @FXML
    private JFXButton btnFinishOrder;
    @FXML
    private JFXDatePicker datePiker;
    @FXML
    private JFXButton btnFaildOrder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            orderService = (OrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
            orderDetailService = (OrderDetailService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER_DETAIL);
            deliverService = (DeliverService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVER);
            cashierService = (CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
            
            UnicastRemoteObject.exportObject(this, 0);
            Subject sub = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
            sub.regesterObserver(this);
            ObserverCollector.getInstance().setOrderSubject(sub,this);
        } catch (Exception ex) {
            Logger.getLogger(ViewOrderForm2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        date = Date();
        initCol();
        loadTable();
        tblGetItem();
        serch();
        loadDeliversComb();
        setTime();
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
                    
                    
                }
                addToOrderDto();

            }
        });
    }

    private void loadTable() {

        try {
            //order Table
            orderList.clear();
            List<OrderDTO> all = orderService.findByDate(date);
            for (OrderDTO o : all) {
                orderList.add(o);
            }
            tblOrders.getItems().setAll(orderList);
            System.out.println(orderList);
        } catch (Exception ex) {
            System.out.println("liss is empty");
            // Logger.getLogger(ViewOrderForm2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    private void serch() {
        try {
            FilteredList<OrderDTO> filteredData = new FilteredList<>(orderList, p -> true);
            txtSerch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(order -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Integer.toString(order.getOid()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (order.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (order.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<OrderDTO> sortedData = new SortedList<>(filteredData);
            tblOrders.setItems(sortedData);
        } catch (Exception e) {
            System.out.println("serch-------------------");
        }

    }

    @FXML
    private void combSetDeliveraAction(ActionEvent event) {
        try {
            String value = combSetDeliver.getValue();
            List<DeliverDTO> all = deliverService.getAll();
            for (DeliverDTO o : all) {
                if (o.getName().equals(value)) {
                    txtDeliverNIC.setText(o.getNic());
                    txtDeliverTelephoneNumber.setText(o.getContact());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ViewOrderForm2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSaveAction(ActionEvent event) {
        if (!txtOid.getText().isEmpty()) {
            if (combSetDeliver.getValue() != null) {
                try {
                    CashierDTO cashierdto = DropBox.getInstance().getCashierdto();
                    orderDTO.setCashier(setDate() + "@" + getCurrentTime() + "\n" + cashierdto.getName() + "\n" + cashierdto.getContact());
                    orderDTO.setDeliver(setDate() + "@" + getCurrentTime() + "\n" + combSetDeliver.getValue() + "\n" + txtDeliverTelephoneNumber.getText());
                    orderDTO.setStatus("Deliverd");
                    orderService.update(orderDTO);
                    loadTable();
                    new Notifi("Deliver Sucess", "Sucessfully deliver Order");
                } catch (Exception ex) {
                    Logger.getLogger(ViewOrderForm2Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Notifi n1 = new Notifi("Selection is Emptyt", "Please select a Deliver", "asd");
            }
        } else {
            Notifi n1 = new Notifi("Selection is Emptyt", "Please select a order", "asd");
        }
    }

    @FXML
    private void btnCancleActionAction(ActionEvent event) {
        combSetDeliver.setValue(null);
        txtDeliverNIC.clear();
        txtDeliverTelephoneNumber.clear();
    }

    private void loadDeliversComb() {
        try {
            List<DeliverDTO> all = deliverService.getAll();
            ObservableList<String> List = FXCollections.observableArrayList();
            for (DeliverDTO dto : all) {
                List.add(dto.getName());
            }
            combSetDeliver.setItems(List);
        } catch (Exception ex) {
            Logger.getLogger(ViewOrderForm2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addToOrderDto() {
        for (OrderDTO o : orderList) {
            if (oid == o.getOid()) {
                orderDTO = o;
            }
        }
        txtOid.setText(orderDTO.getOid() + "");
        txtCustomerName.setText(orderDTO.getName());
        txtCustomerTelephoneNo.setText(orderDTO.getContact());
    }

    private void setTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            //second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            hour = cal.get(Calendar.HOUR);
            //System.out.println(hour + ":" + (minute) + ":" + second);
            txtTime.setText(hour + ":" + (minute));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public String setDate() {
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd");
        return (sdf.format(d1));
    }

    @FXML
    private void btnFinishOrderAction(ActionEvent event) {
        if (!txtOid.getText().isEmpty()) {
            try {
                orderDTO.setStatus("Finished");
                orderService.update(orderDTO);
                loadTable();
                new Notifi("Finish Sucess", "Sucessfully Finish Order");
            } catch (Exception ex) {
                Logger.getLogger(ViewOrderForm2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Notifi n1 = new Notifi("Selection is Emptyt", "Please select a order", "asd");
        }
    }

    @FXML
    private void datePikerAction(ActionEvent event) {
        date = datePiker.getValue() + "";
        loadTable();
    }

    @FXML
    private void btnFaildOrderAction(ActionEvent event) {
        if (!txtOid.getText().isEmpty()) {
            try {
                orderDTO.setStatus("Faild");
                orderService.update(orderDTO);
                loadTable();
                new Notifi("Faidld Sucess", "Sucessfully faild Order");
            } catch (Exception ex) {
                Logger.getLogger(ViewOrderForm2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Notifi n1 = new Notifi("Selection is Emptyt", "Please select a order", "asd");
        }
    }
    public String getCurrentTime() {
        Date date = new Date();
        String strDateFormat = "hh:mm a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
       // System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
       return formattedDate;
    }

    @Override
    public void updateObserver() throws Exception {
        loadTable();
    }
}
