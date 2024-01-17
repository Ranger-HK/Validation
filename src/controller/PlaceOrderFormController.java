package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.ItemDetails;
import model.Order;
import view.tm.CartTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Created By Ravindu Prathibha
 * @created 1/15/2024 - 2:14 PM
 * @project Thogakade
 */
public class PlaceOrderFormController {
    public AnchorPane txtContext;
    public TableView<CartTM> tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public ComboBox<String> cmbCustomer;
    public Label lblOrderId;
    public Label lblDate;
    public Label lblTime;
    public ComboBox<String> cmbItem;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnClear;
    public JFXButton btnPlaceOrder;
    public Label lblTotal;
    public JFXButton btnAddToCart;


    int cartSelectedRowForRemove = -1;
    ObservableList<CartTM> observableList = FXCollections.observableArrayList();

    public void initialize() {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        try {
            loadDateAndTime();
            loadCustomerIds();
            loadItemIds();
            setOrderId();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Load Customer ComboBox Data To TextField
        cmbCustomer.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }));

        //Load Item ComboBox Data To TextField
        cmbItem.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }));

        //table row remove
        tblItem.getSelectionModel().selectedIndexProperty().addListener(((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        }));
    }

    private void setOrderId() {
        try {
            lblOrderId.setText(new OrderController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Load Item ComboBox Data To TextField
    private void setItemData(String orderId) throws SQLException, ClassNotFoundException {
        Item item = new ItemController().getItem(orderId);
        if (item == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDescription.setText(item.getDescription());
            txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        }
    }

    //Load Customer ComboBox Data To TextField
    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        Customer customer = new CustomerController().getCustomer(customerId);
        if (customer == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtCustomerName.setText(customer.getName());
            txtCustomerAddress.setText(customer.getAddress());
            txtCustomerSalary.setText(String.valueOf(customer.getSalary()));
        }
    }

    //Item Ids Combo Box Load
    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getItemIds();
        cmbItem.getItems().addAll(itemIds);
    }

    //Customer Ids Combo Box Load
    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomer.getItems().addAll(customerIds);
    }

    //Add To Table Select Data From TextField

    private void loadDateAndTime() {
        //Set Date
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        //Set Time
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime localTime = LocalTime.now();
            lblTime.setText(
                    localTime.getHour() + " : " + localTime.getMinute() + " : " + localTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        String description = txtDescription.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyForCustomer = Integer.parseInt(txtQty.getText());
        double total = qtyForCustomer * unitPrice;

        if (qtyOnHand < qtyForCustomer) {
            new Alert(Alert.AlertType.WARNING, "Invalid Qty").show();
            return;
        }


        CartTM cartTM = new CartTM(
                cmbItem.getValue(),
                description,
                qtyForCustomer,
                unitPrice,
                total
        );

        int rowNumber = isExists(cartTM);

        if (isExists(cartTM) == -1) {
            observableList.add(cartTM);

        } else {
            CartTM tempTm = observableList.get(rowNumber);
            CartTM newTm = new CartTM(
                    tempTm.getCode(),
                    tempTm.getDescription(),
                    tempTm.getQty() + qtyForCustomer,
                    unitPrice,
                    total + tempTm.getTotal()
            );

            if (qtyOnHand < tempTm.getQty()) {
                new Alert(Alert.AlertType.WARNING, "Invalid Qty").show();
                return;
            }

            observableList.remove(rowNumber);
            observableList.add(newTm);
        }

        tblItem.setItems(observableList);
        calculateCost();

    }

    //Fix Row Case
    private int isExists(CartTM tm) {
        for (int i = 0; i < observableList.size(); i++) {
            if (tm.getCode().equals(observableList.get(i).getCode())) {
                return i;
            }
        }
        return -1;
    }

    //calculate total
    void calculateCost() {
        double ttl = 0;
        for (CartTM tms : observableList
        ) {
            ttl += tms.getTotal();

        }
        lblTotal.setText(ttl + " /=");
    }


    //delete table row
    public void btnClearOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a Row").show();
        } else {
            observableList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblItem.refresh();
        }

    }


    //Save order
    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        ArrayList<ItemDetails> itemDetails = new ArrayList<>();
        double ttl = 0;
        for (CartTM tempTm : observableList
        ) {
            ttl += tempTm.getTotal();
            itemDetails.add(new ItemDetails(
                    tempTm.getCode(),
                    tempTm.getUnitPrice(),
                    tempTm.getQty()
            ));
        }

        Order order = new Order(
                lblOrderId.getText(),
                cmbCustomer.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                ttl,
                itemDetails
        );

        if (new OrderController().placeOrder(order)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }


    }
}
