package util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * @Created By Ravindu Prathibha
 * @created 1/19/2024 - 5:49 PM
 * @project Validation
 */
public class Validation {

    //print HashMap Values
    public static Object validate(LinkedHashMap<TextField,Pattern> allValidations, JFXButton btnSaveCustomer){
        btnSaveCustomer.setDisable(true);

        //print values
        for (TextField textField : allValidations.keySet()){
            Pattern pattern = allValidations.get(textField);

            //printed vales check and return Error TextField
            if (!pattern.matcher(textField.getText()).matches()){
                if (!textField.getText().isEmpty()) {
                    textField.setStyle("-fx-background-color: red");
                }
                return textField;
            }
            textField.setStyle("-fx-background-color: green");
        }
        // printed vales check and return true (Not Error)
        btnSaveCustomer.setDisable(false);
        return true;
    }
}
