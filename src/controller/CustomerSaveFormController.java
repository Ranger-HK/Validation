package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;

import java.sql.SQLException;
import java.util.LinkedHashMap;
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

    //Create Hash Map
    LinkedHashMap<TextField,Pattern>allValidations=new LinkedHashMap<>();

    //Set regex
    Pattern idPattern = Pattern.compile("^(C00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{4,12}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,32}$");
    Pattern salaryPattern = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");


    public void initialize() {
        validateInit();
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

    public void txtClear() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerSalary.clear();
    }


    //Store Values in  HashMap
    private void validateInit() {
        btnSaveCustomer.setDisable(true);
        allValidations.put(txtCustomerId,idPattern);
        allValidations.put(txtCustomerName,namePattern);
        allValidations.put(txtCustomerAddress,addressPattern);
        allValidations.put(txtCustomerSalary,salaryPattern);

    }


    //get response Of validate (Collect issue textField) method and set EnterKey Press
    public void textFieldOnKeyReleased(KeyEvent keyEvent) {

        //get response
        Object response = validate();

        //enter key press event --> Response is error or Response is true
        //Response is error -- check textFiled Object
        if (keyEvent.getCode()==KeyCode.ENTER){
            if (response instanceof TextField){
                TextField textField = (TextField) response;
                textField.requestFocus();

            // Response is true -- check boolean
            }else if(response instanceof Boolean){
                new Alert(Alert.AlertType.INFORMATION,"Done").showAndWait();
            }
        }
    }

    //print HashMap Values
    private Object validate(){
        btnSaveCustomer.setDisable(true);

        //print values
        for (TextField textField : allValidations.keySet()){
            Pattern pattern = allValidations.get(textField);

            //printed vales check and return Error TextField
            if (!pattern.matcher(textField.getText()).matches()){
                if (!textField.getText().isEmpty()) {
                    addErrorToTheBoard(textField);
                }
                return textField;
            }
            removeError(textField);
        }
        // printed vales check and return true (Not Error)
        btnSaveCustomer.setDisable(false);
        return true;
    }

    //Error Code
    private void removeError(TextField textField) {
        textField.setStyle("-fx-background-color: green");
    }

    //Right Code
    private void addErrorToTheBoard(TextField textField) {
        textField.setStyle("-fx-background-color: red");

    }
}
