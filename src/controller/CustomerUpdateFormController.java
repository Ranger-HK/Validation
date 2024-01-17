package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.SQLException;

/**
 * @Created By Ravindu Prathibha
 * @created 1/13/2024 - 12:02 PM
 * @project Thogakade
 */
public class CustomerUpdateFormController {
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXButton btnUpdateCustomer;

    public void updateCusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1=new Customer(
                txtCustomerId.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                Double.parseDouble(txtCustomerSalary.getText())
        );

        if (new CustomerController().updateCustomer(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successful").show();
            txtClear();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...");

        }

    }

    public void loadCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtCustomerId.getText();
        Customer customer = new CustomerController().getCustomer(customerId);
        if (customer == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(customer);
        }
    }

    void setData(Customer customer){
        txtCustomerId.setText(customer.getCustomerId());
        txtCustomerName.setText(customer.getName());
        txtCustomerAddress.setText(customer.getAddress());
        txtCustomerSalary.setText(String.valueOf(customer.getSalary()));
    }

    public void moveAddressOnAction(ActionEvent actionEvent) {
        txtCustomerAddress.requestFocus();
    }

    public void moveSalaryOnAction(ActionEvent actionEvent) {
        txtCustomerSalary.requestFocus();
    }

    public void txtClear(){
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerSalary.clear();
    }


}
