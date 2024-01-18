package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;

import java.sql.SQLException;
import java.util.regex.Pattern;

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

    public void initialize() {
        btnSaveCustomer.setDisable(true);
    }

    public void SaveCusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                txtCustomerId.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                Double.parseDouble(txtCustomerSalary.getText())
        );

        if (new CustomerController().saveCustomer(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved").show();
            txtClear();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }

    }

    public void moveNameOnAction(ActionEvent actionEvent) {
    }

    public void moveAddresOnAction(ActionEvent actionEvent) {

    }

    public void moveSalaryOnAction(ActionEvent actionEvent) {
    }

    public void txtClear() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerSalary.clear();
    }


    public void textFieldOnKeyReleased(KeyEvent keyEvent) {

        String idRegEx = "^(C00-)[0-9]{3,4}$";
        String nameRegEx = "^[A-z ]{4,12}$";
        String addressRegEx = "^[A-z0-9/ ]{6,32}$";
        String salaryRegEx = "^[1-9][0-9]*([.][0-9]{2})?$";

        Pattern idPattern = Pattern.compile(idRegEx);
        Pattern namePattern = Pattern.compile(nameRegEx);
        Pattern addressPattern = Pattern.compile(addressRegEx);
        Pattern salaryPattern = Pattern.compile(salaryRegEx);

        String typeTextId = txtCustomerId.getText();
        String typeTextName = txtCustomerName.getText();
        String typeTextAddress = txtCustomerAddress.getText();
        String typeTextSalary = txtCustomerSalary.getText();


        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (idPattern.matcher(typeTextId).matches()) {
                txtCustomerId.setStyle("-fx-background-color: green");
                txtCustomerName.requestFocus();


                if (namePattern.matcher(typeTextName).matches()) {
                    txtCustomerName.setStyle("-fx-background-color: green");
                    txtCustomerAddress.requestFocus();


                    if (addressPattern.matcher(typeTextAddress).matches()) {
                        txtCustomerAddress.setStyle("-fx-background-color: green");
                        txtCustomerSalary.requestFocus();


                        if (salaryPattern.matcher(typeTextSalary).matches()) {
                            txtCustomerSalary.setStyle("-fx-background-color: green");
                            btnSaveCustomer.requestFocus();


                        } else {
                            txtCustomerSalary.setStyle("-fx-background-color: red");
                            txtCustomerSalary.requestFocus();
                        }

                    } else {
                        txtCustomerAddress.setStyle("-fx-background-color: red");
                        txtCustomerAddress.requestFocus();
                    }


                } else {
                    txtCustomerName.setStyle("-fx-background-color: red");
                    txtCustomerName.requestFocus();
                }


            } else {
                txtCustomerId.setStyle("-fx-background-color: red");
                txtCustomerId.requestFocus();

            }
        }

    }
}
