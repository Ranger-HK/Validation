package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.SQLException;

/**
 * @Created By Ravindu Prathibha
 * @created 1/11/2024 - 3:49 PM
 * @project Thogakade
 */
public class CustomerDeleteFormController {
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXButton btnDeleteCustomer;

    public void DeleteCusOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

            if ( new CustomerController().deleteCustomer(txtCustomerId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Successful").show();
                txtClear();

            }else {

                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
            }
    }

    public void customerSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
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

    public void txtClear(){
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerSalary.clear();
    }
}

