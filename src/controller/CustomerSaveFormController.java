package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.SQLException;

/**
 * @Created By Ravindu Prathibha
 * @created 1/10/2024 - 6:13 PM
 * @project Thogakade
 */
public class CustomerSaveFormController {

    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXButton btnSaveCustomer;

    public void SaveCusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1=new Customer(
                txtCustomerId.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                Double.parseDouble(txtCustomerSalary.getText())
        );

        if (new CustomerController().saveCustomer(c1)){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved").show();
                txtClear();

        }else {
                new Alert(Alert.AlertType.WARNING,"Try Again").show();

        }

    }

    public void moveNameOnAction(ActionEvent actionEvent) {
        txtCustomerName.requestFocus();

    }

    public void moveAddresOnAction(ActionEvent actionEvent) {
        txtCustomerAddress.requestFocus();
    }

    public void moveSalaryOnAction(ActionEvent actionEvent) {
        txtCustomerSalary.requestFocus();

    }

    public void txtClear() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerSalary.clear();
    }
}
