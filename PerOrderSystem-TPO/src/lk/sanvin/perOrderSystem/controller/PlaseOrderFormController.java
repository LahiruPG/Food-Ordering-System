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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import lk.sanvin.perOrderSystem.dto.TPODTO;
import lk.sanvin.perOrderSystem.proxy.ProxyHandler;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.custom.ItemService;
import lk.sanvin.perOrderSystem.service.custom.OrderService;
import lk.sanvin.perOrderSystem.util.DropBox;
import lk.sanvin.perOrderSystem.util.Notifi;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class PlaseOrderFormController implements Initializable {
    //Time
    private int minute;
    //private int second;
    private int hour;

    ObservableList<OrderDetailDTO> list = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtTelephoneNumber;
    @FXML
    private JFXTextArea txtAddress;
    @FXML
    private TableView<OrderDetailDTO> tblItem;
    @FXML
    private TableColumn<OrderDetailDTO, String> clmDescription;
    @FXML
    private TableColumn<OrderDetailDTO, Double> clmUnitPrice;
    @FXML
    private TableColumn<OrderDetailDTO, Double> clmAmount;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXButton btnAddToList;
    @FXML
    private JFXTextField txtDate;
    @FXML
    private JFXDatePicker datePiker;
    @FXML
    private JFXTimePicker timePiker;
    @FXML
    private JFXButton btnRemoveItem;
    @FXML
    private JFXButton btnSaveOrder;
    @FXML
    private JFXButton btnCancleOrder;

    private int id;
    private int qty;
    private double unitPrice;
    private double amount;
    private String discription;
    private OrderDetailDTO tableDTO;

    private ItemService itemService;
    private OrderService orderService;
    @FXML
    private JFXComboBox<String> comBoxDescription;
    @FXML
    private TableColumn<OrderDetailDTO, Integer> clmQty;
    @FXML
    private TextField txtTotalAmount;
    @FXML
    private TextField txtTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            itemService = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITM);
            orderService = (OrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
        } catch (Exception ex) {
            Logger.getLogger(PlaseOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadCombo();
        initCol();
        clmQty.setEditable(true);
        tblGetItem();
        setDate();
        setTime();
        

    }

    @FXML
    private void btnAddToListAction(ActionEvent event) {

        qty = Integer.parseInt(txtQty.getText());
        OrderDetailDTO dto = new OrderDetailDTO(comBoxDescription.getValue(), Integer.parseInt(txtQty.getText()), unitPrice, unitPrice * qty);
        for (OrderDetailDTO o : list) {
            boolean b = o.getDiscription() == dto.getDiscription();
            if (b) {
                o.setQty(dto.getQty());
                o.setAmount(dto.getQty() * o.getUnitPrice());
                tblItem.getItems().setAll(list);
               // clear();
                return;
            }

        }
        list.add(dto);
        tblItem.getItems().setAll(list);
        getTotalAmount();
        txtQty.setText("");
       // comBoxDescription.setValue("Discription");
    }

    @FXML
    private void btnRemoveItemAction(ActionEvent event) {
        for (OrderDetailDTO o : list) {
            if (!tableDTO.equals(o)) {
                list.remove(o);
                tblItem.getItems().setAll(list);
                return;
            }
        }
        tblItem.getItems().setAll(list);
        getTotalAmount();
    }

    @FXML
    private void btnSaveOrderAction(ActionEvent event) {

        if (txtCustomerName.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
            if (txtTelephoneNumber.getText().matches("^[0-9]{3}-[0-9]{7}$")) {
                if (txtAddress.getText().matches("^([A-Z a-z0-9.,\\n]){1,95}.$")) {
                    if (validatDatePiker()) {
                        if (validatTimePiker()) {
                            if (!list.isEmpty()) {
                                saveOrder();
                                clear();
                                new Notifi("Added Sucess", "Sucessfully added new Order");

                            } else {
                                Notifi n1 = new Notifi("Check Order Table", "No items found", "asd");
                            }
                        } else {
                            Notifi n1 = new Notifi("Invalid Input", "Check Time", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Date", "asd");
                    }

                } else {
                    Notifi n1 = new Notifi("Invalid Input", "Check Address", "asd");
                }
            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Telephone Number", "asd");
            }
        } else {
            Notifi n1 = new Notifi("Invalid Input", "Check Customer Name", "asd");
        }
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
                    unitPrice = o.getAmount();
                }
            }
        } catch (Exception ex) {
            System.out.println("Item list is null");
          //  Logger.getLogger(PlaseOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("discription"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        clmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void tblGetItem() {
        tblItem.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    tableDTO = new OrderDetailDTO(tblItem.getSelectionModel().getSelectedItem().getDiscription(), tblItem.getSelectionModel().getSelectedItem().getQty(), tblItem.getSelectionModel().getSelectedItem().getUnitPrice(), tblItem.getSelectionModel().getSelectedItem().getAmount());
                    comBoxDescription.setValue(tableDTO.getDiscription());
                    getTotalAmount();
                } catch (Exception e) {
                    System.out.println("Selection is null");
                }
            }
        });
    }

    private void getTotalAmount() {
        double d = 0;
        for (OrderDetailDTO o : list) {
            d += o.getAmount();
        }
        txtTotalAmount.setText(d + "");
    }

    public void setDate() {
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd");
        txtDate.setText(sdf.format(d1));
    }
    private void setTime(){
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

    private void saveOrder() {
        TPODTO tpodto = DropBox.getInstance().getTpodto();
        try {
            OrderDTO order = new OrderDTO();
            List<OrderDetailDTO> detailList = new ArrayList<>();
            for (OrderDetailDTO orderTableDTO : list) {
                detailList.add(orderTableDTO);
            }
            order.setTop(txtDate.getText() + "@" + getCurrentTime()+"\n"+tpodto.getName() + "\n" + tpodto.getContact());
            order.setName(txtCustomerName.getText());
            order.setContact(txtTelephoneNumber.getText());
            order.setAddress(txtAddress.getText());
            order.setDate(txtDate.getText());
            order.setDeliveryDateTime(datePiker.getValue() + "@" + timePiker.getValue());
            order.setAmount(Double.parseDouble(txtTotalAmount.getText()));
            order.setOrderDetails(detailList);
            orderService.add(order);
            System.out.println(order);
        } catch (Exception ex) {
            Logger.getLogger(PlaseOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnCancleOrderAction(ActionEvent event) {
        clear();
    }

    private boolean validatDatePiker() {
        try {
            String toString = datePiker.getValue().toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean validatTimePiker() {
        try {
            String toString = timePiker.getValue().toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void clear() {
        txtCustomerName.clear();
        txtAddress.clear();
        txtTelephoneNumber.clear();
        txtQty.clear();
        txtTotalAmount.clear();
        datePiker.setValue(null);
        timePiker.setValue(null);
        comBoxDescription.setValue(null);
        list.clear();
        tblItem.refresh();
        tblItem.getItems().setAll(list);
    }
    private String getCurrentTime() {
        Date date = new Date();
        String strDateFormat = "hh:mm a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
       // System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
       return formattedDate;
    }
}
