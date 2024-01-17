package controller;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Created By Ravindu Prathibha
 * @created 1/15/2024 - 4:12 PM
 * @project Thogakade
 */
public interface CustomerService {
    boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    Customer getCustomer(String id) throws SQLException, ClassNotFoundException;

    ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException;

}
