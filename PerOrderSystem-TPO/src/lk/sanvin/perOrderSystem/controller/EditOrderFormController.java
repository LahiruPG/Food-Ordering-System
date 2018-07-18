/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
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
import lk.sanvin.perOrderSystem.dto.ItemDTO;
import lk.sanvin.perOrderSystem.dto.OrderDTO;
import lk.sanvin.perOrderSystem.dto.OrderDetailDTO;
import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.observer.Subject;
import lk.sanvin.perOrderSystem.proxy.ProxyHandler;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.custom.ItemService;
import lk.sanvin.perOrderSystem.service.custom.OrderDetailService;
import lk.sanvin.perOrderSystem.service.custom.OrderService;
import lk.sanvin.perOrderSystem.util.Notifi;
import lk.sanvin.perOrderSystem.util.ObserverCollector;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class EditOrderFormController implements Initializable ,Observer{

    private ObservableList<OrderDTO> orderList = FXCollections.observableArrayList();
    private ObservableList<OrderDetailDTO> orderDetailList = FXCollections.observableArrayList();

    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private ItemService itemService;

    private int oid;
    private int odid;

    private OrderDTO orderDTO;
    private OrderDetailDTO orderDetailDTO;
    private ItemDTO itemDTO;
    //Time
    private int minute;
    //private int second;
    private int hour;

    private String date;
    private String time;
    private boolean enableRemoverOrderBtn;

    @FXML
    private JFXTextField txtSerch;
    @FXML
    private JFXDatePicker datePiker;
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
    private TableColumn<OrderDetailDTO, String> clmQty;
    @FXML
    private TableColumn<OrderDetailDTO, String> clmUnitPrice;
    @FXML
    private TableColumn<OrderDetailDTO, String> ckmAmount;
    @FXML
    private TextField txtTotalQty;
    @FXML
    private TextField txtTotalAmount;
    @FXML
    private JFXButton btnAddToList;
    @FXML
    private JFXTextField txtOid;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtCustomerTelephoneNo;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXComboBox<String> comBoxDescription;
    @FXML
    private JFXButton btnRemoveItem;
    @FXML
    private JFXTextArea txtAddress;
    @FXML
    private JFXDatePicker datePikerEditDate;
    @FXML
    private JFXTimePicker timePikerEditOrder;
    @FXML
    private JFXButton btnUpdateOrder;
    @FXML
    private JFXButton btnCancle;
    @FXML
    private JFXButton btnClearDateTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            orderService = (OrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
            orderDetailService = (OrderDetailService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER_DETAIL);
            itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITM);

            UnicastRemoteObject.exportObject(this, 0);
            Subject sub = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
            sub.regesterObserver(this);
            ObserverCollector.getInstance().setOrderSubject(sub,this);
        } catch (Exception ex) {
            Logger.getLogger(EditOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        date = Date();
        initCol();
        loadTable();
        tblGetItem();
        OrderDetailtblGetItem();
        serch();
        setTime();
        loadCombo();
    }

    @FXML
    private void datePikerAction(ActionEvent event) {
        date = datePiker.getValue() + "";
        loadTable();
    }

    @FXML
    private void btnAddToListAction(ActionEvent event) {
        OrderDetailDTO ods = new OrderDetailDTO(itemDTO.getDiscription(), Integer.parseInt(txtQty.getText()), itemDTO.getAmount(), itemDTO.getAmount() * Integer.parseInt(txtQty.getText()));
        ods.setOrderDTO(orderDTO);
        System.out.println(ods);
        /* try {
        
        //  orderDetailService.add(orderDetailDTO);
        } catch (Exception ex) {
        Logger.getLogger(EditOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    private void loadCombo() {
        try {
            List<ItemDTO> all = itemService.getAll();
            ObservableList<String> List = FXCollections.observableArrayList();
            for (ItemDTO dto : all) {
                List.add(dto.getDiscription());
            }
            comBoxDescription.setItems(List);
        } catch (Exception ex) {
            Logger.getLogger(PlaseOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void comBoxDescriptionAction(ActionEvent event) {

        try {

            List<ItemDTO> all = itemService.getAll();
            for (ItemDTO o : all) {
                if (comBoxDescription.getValue().equals(o.getDiscription())) {
                    itemDTO = o;
                    //txtQty.setText(o.getAmount() + "");
                }
            }
        } catch (Exception ex) {
            System.out.println("Item list is null");
            //  Logger.getLogger(PlaseOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                    enableRemoverOrderBtn = false;
                } catch (Exception ex) {
                    Logger.getLogger(EditOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void setTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            //second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            hour = cal.get(Calendar.HOUR);
            //System.out.println(hour + ":" + (minute) + ":" + second);
            time = (hour + ":" + (minute));
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

    private void addToOrderDto() {
        for (OrderDTO o : orderList) {
            if (oid == o.getOid()) {
                orderDTO = o;
            }
        }
        txtOid.setText(orderDTO.getOid() + "");
        txtCustomerName.setText(orderDTO.getName());
        txtCustomerTelephoneNo.setText(orderDTO.getContact());
        txtAddress.setText(orderDTO.getAddress());
    }

    private void OrderDetailtblGetItem() {
        tblOrderDetails.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                try {
                    odid = tblOrderDetails.getSelectionModel().getSelectedItem().getId();
                    List<OrderDetailDTO> odList = orderDetailService.finbyid(odid);
                    for (OrderDetailDTO dto : odList) {
                        orderDetailDTO = dto;
                    }
                    enableRemoverOrderBtn = true;
                    System.out.println(orderDetailDTO);
                } catch (Exception ex) {
                    Logger.getLogger(EditOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    @FXML
    private void btnRemoveItemAction(ActionEvent event) {
        if (enableRemoverOrderBtn) {
            try {
                orderDetailDTO.setOrderDTO(orderDTO);
                orderDetailService.remove(orderDetailDTO);

                orderDetailList.clear();
                List<OrderDetailDTO> odList = orderDetailService.findByOid(oid);
                for (OrderDetailDTO dto : odList) {
                    orderDetailList.add(dto);
                }
                tblOrderDetails.getItems().setAll(orderDetailList);
                getTotalQty();
                getTotaAmount();
                loadTable();
            } catch (Exception ex) {
                Logger.getLogger(EditOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Notifi n1 = new Notifi("Empty Selection", "Please Select Item", "asd");
        }

    }

    @FXML
    private void btnUpdateOrderActiom(ActionEvent event) {
        try {
            orderDTO.setAddress(txtAddress.getText());
            orderDTO.setContact(txtCustomerTelephoneNo.getText());
            orderDTO.setName(txtCustomerName.getText());
            if (datePikerEditDate.getValue() != null || timePikerEditOrder.getValue() != null) {
                orderDTO.setDeliveryDateTime(datePikerEditDate.getValue() + "@" + timePikerEditOrder.getValue());
            }
            orderService.update(orderDTO);
        } catch (Exception ex) {
            Logger.getLogger(EditOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Notifi n1 = new Notifi("Update Sucess", "Sucessfully update order");
        datePikerEditDate.setValue(null);
        timePikerEditOrder.setValue(null);
        loadTable();
    }

    @FXML
    private void btnCancleAction(ActionEvent event) {
        try {
            orderDTO.setStatus("CANCELLED");
            orderService.update(orderDTO);
            loadTable();
            
        } catch (Exception ex) {
            Logger.getLogger(EditOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);

    }

    @FXML
    private void btnClearDateTimeAction(ActionEvent event) {
        datePikerEditDate.setValue(null);
        timePikerEditOrder.setValue(null);
    }

    @Override
    public void updateObserver() throws Exception {
        loadTable();
    }
}
