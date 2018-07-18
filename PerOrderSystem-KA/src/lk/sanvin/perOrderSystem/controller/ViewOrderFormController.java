/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
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
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lk.sanvin.perOrderSystem.dto.CookDTO;
import lk.sanvin.perOrderSystem.dto.OrderDTO;
import lk.sanvin.perOrderSystem.dto.OrderDetailDTO;
import lk.sanvin.perOrderSystem.observer.Observer;
import lk.sanvin.perOrderSystem.observer.Subject;
import lk.sanvin.perOrderSystem.proxy.ProxyHandler;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
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
public class ViewOrderFormController implements Initializable, Observer {

    private ObservableList<OrderDTO> orderList = FXCollections.observableArrayList();
    private ObservableList<OrderDetailDTO> orderDetailList = FXCollections.observableArrayList();
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private int oid;
    private OrderDTO orderDTO;
    //Time
    private int minute;
    //private int second;
    private int hour;
    private String time;
    
    private String totalAmount;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private Pane paneDrawer;
    @FXML
    private JFXButton btnFinishOrder;
    @FXML
    private JFXButton btnTakeOrder;
    @FXML
    private Pane paneMain;
    private int a = 0;
    private AnimationTimer timer;
    @FXML
    private Pane mainPain;
    private StackPane stackPane;
    @FXML
    private TableView<OrderDTO> tblOrders;
    @FXML
    private TableColumn<OrderDTO, String> clmCustomerName;
    @FXML
    private TableColumn<OrderDTO, String> clmTelephoneNumber;
    @FXML
    private TableColumn<OrderDTO, String> clmDeliveryTime;
    @FXML
    private TableColumn<OrderDTO, String> clmTOP;
    @FXML
    private TableView<OrderDetailDTO> tblOrderDetails;
    @FXML
    private TableColumn<OrderDetailDTO, String> clmDescription;
    @FXML
    private TableColumn<OrderDetailDTO, Integer> clmQty;
    @FXML
    private TextField txtTotalQty;
    @FXML
    private TableView<OrderDetailDTO> tblGetOrder;
    @FXML
    private TableColumn<OrderDetailDTO, String> clmGetOrderDescription;
    @FXML
    private TableColumn<OrderDetailDTO, Integer> clmTakeOrderQty;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtTelephoneNumber;
    @FXML
    private JFXTextField txtDeliveryTime;
    private OrderDTO dto;
    @FXML
    private TextField txtTotalQty1;
    @FXML
    private AnchorPane pnePrint;
    @FXML
    private Label lblOid;
    @FXML
    private Label lblCustomerName;
    @FXML
    private Label lblTelephoneNo;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblAmount;
    @FXML
    private VBox vboxPrint;
    @FXML
    private TextField txtTotalAmount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            orderService = (OrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
            orderDetailService = (OrderDetailService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER_DETAIL);
            UnicastRemoteObject.exportObject(this, 0);
            Subject sub = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER);
            sub.regesterObserver(this);
            ObserverCollector.getInstance().setOrderSubject(sub, this);

        } catch (Exception ex) {
            Logger.getLogger(ViewOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        drawer.setSidePane(paneDrawer);

        initCol();
        loadTable();
        tblGetItem();
        setTime();
    }

    @FXML
    private void btnFinishOrderAction(ActionEvent event) {

        drawer.close();
        BoxBlur bb = new BoxBlur();
        bb.setWidth(5);
        bb.setHeight(5);
        bb.setIterations(0);
        mainPain.setEffect(bb);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ++a;
                if (a == 20) {
                    drawer.setVisible(false);
                    paneDrawer.setVisible(false);
                    btnTakeOrder.setDisable(false);
                    timer.stop();
                    a = 0;
                }
            }
        };
        timer.start();

        try {
            dto.setStatus("Ready to delivery");
            orderService.update(dto);
            
        } catch (Exception ex) {
            Logger.getLogger(ViewOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
        printBill();
        
    }

    @FXML
    private void btnTakeOrderAction(ActionEvent event) {
        
        if (orderList.isEmpty()) {
            Notifi n1 = new Notifi("Check Order Table", "No Orders found", "asd");
        } else {
            paneDrawer.setVisible(true);
            drawer.setVisible(true);
            drawer.open();
            BoxBlur bb = new BoxBlur();
            bb.setWidth(5);
            bb.setHeight(5);
            bb.setIterations(3);
            mainPain.setEffect(bb);
            btnTakeOrder.setDisable(true);

            dto = orderList.get(0);
            System.out.println(dto);
            txtCustomerName.setText(dto.getName());
            txtTelephoneNumber.setText(dto.getContact());
            txtDeliveryTime.setText(dto.getDeliveryDateTime());

            List<OrderDetailDTO> odList;
            try {
                orderDetailList.clear();
                odList = orderDetailService.findByOid(dto.getOid());
                for (OrderDetailDTO dto : odList) {
                    orderDetailList.add(dto);
                }
                tblGetOrder.getItems().setAll(orderDetailList);
                int d = 0;
                for (OrderDetailDTO o : orderDetailList) {
                    d += o.getQty();
                }
                txtTotalQty1.setText(d + "");
                CookDTO cookdto = DropBox.getInstance().getCookdto();
                dto.setCook(Date() + "@" + getCurrentTime() + "\n" + cookdto.getName() + "\n" + cookdto.getContact());
                dto.setStatus("Cooking");
                orderDTO=dto;
                orderService.update(dto);

            } catch (Exception ex) {
                Logger.getLogger(ViewOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void initCol() {
        //Main Tabale
        clmCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmTelephoneNumber.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmDeliveryTime.setCellValueFactory(new PropertyValueFactory<>("deliveryDateTime"));
        clmTOP.setCellValueFactory(new PropertyValueFactory<>("top"));
        //orderDetail tabel
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("discription"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        //Take Order Tabale
        clmGetOrderDescription.setCellValueFactory(new PropertyValueFactory<>("discription"));
        clmTakeOrderQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    private void loadTable() {
        try {
            orderList.clear();
            List<OrderDTO> asd = orderService.finbyStatusAndDate("Pending", Date());
            List<OrderDTO> all = asd;
            for (OrderDTO o : all) {
                orderList.add(o);
            }
            tblOrders.getItems().setAll(orderList);

        } catch (Exception ex) {
            Logger.getLogger(ViewOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
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
                } catch (Exception ex) {
                    System.out.println("list is empty");
                }

            }
        });
    }

    private void setTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            //second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            hour = cal.get(Calendar.HOUR);
            //System.out.println(hour + ":" + (minute) + ":" + second);
            time = hour + ":" + (minute);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public String Date() {
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd");
        return (sdf.format(d1));
    }

    private void getTotalQty() {
        int d = 0;
        double s=0;
        for (OrderDetailDTO o : orderDetailList) {
            d += o.getQty();
            s+=o.getAmount();
        }
        txtTotalQty.setText(d + "");
        txtTotalAmount.setText(s+"");
    }
   

    @Override
    public void updateObserver() throws Exception {
        loadTable();
    }

    private String getCurrentTime() {
        Date date = new Date();
        String strDateFormat = "hh:mm a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        // System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
        return formattedDate;
    }

    private void print(Node node) {
        System.out.println("Creating a printer job...");

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            System.out.println(job.jobStatusProperty().asString());

            boolean printed = job.printPage(node);
            if (printed) {
                job.endJob();
            } else {
                System.out.println("Printing failed.");
            }
        } else {
            System.out.println("Could not create a printer job.");
        }
    }
    private void printBill(){
        lblOid.setText("Order id:  "+orderDTO.getOid());
        lblCustomerName.setText("Customer Name :  "+orderDTO.getName());
        lblTelephoneNo.setText("Telephone No :  "+orderDTO.getContact());
        lblAddress.setText("Address :  "+orderDTO.getAddress());
        lblAmount.setText("Amount:  "+txtTotalAmount);
        
        pnePrint.setVisible(true);
        print(vboxPrint);
        pnePrint.setVisible(false);
    }
}
