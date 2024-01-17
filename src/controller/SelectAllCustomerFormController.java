package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import view.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Created By Ravindu Prathibha
 * @created 1/13/2024 - 2:55 PM
 * @project Thogakade
 */
public class SelectAllCustomerFormController {
    public TableView <CustomerTM> tblCustomer;
    public TableColumn colCusId;
    public TableColumn colCusName;
    public TableColumn colCusAddress;
    public TableColumn coCusSalary;

    public void initialize(){
        try {
            colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            coCusSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

           setCustomersToTable(new CustomerController().getAllCustomers());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCustomersToTable(ArrayList<Customer> customers) {
        ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();
        customers.forEach(e->{
            observableList.add(new CustomerTM(e.getCustomerId(),e.getName(),e.getAddress(),e.getSalary()));
        });
        tblCustomer.setItems(observableList);
    }

}
