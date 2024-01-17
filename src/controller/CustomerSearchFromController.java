package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.SQLException;

/**
 * @Created By Ravindu Prathibha
 * @created 1/11/2024 - 3:50 PM
 * @project Thogakade
 */
public class CustomerSearchFromController {

    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXButton btnSearchCustomer;

    public void SearchCusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtCustomerId.getText();
        Customer customer = new CustomerController().getCustomer(customerId);
        if (customer == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(customer);
        }
    }

    void setData(Customer customer) {
        txtCustomerId.setText(customer.getCustomerId());
        txtCustomerName.setText(customer.getName());
        txtCustomerAddress.setText(customer.getAddress());
        txtCustomerSalary.setText(String.valueOf(customer.getSalary()));
    }

    public void moveNameOnAction(ActionEvent actionEvent) {

    }

    public void moveSalaryOnAction(ActionEvent actionEvent) {

    }

    public void moveAddresOnAction(ActionEvent actionEvent) {

    }
}
