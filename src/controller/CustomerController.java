package controller;

import db.DBConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created By Ravindu Prathibha
 * @created 1/15/2024 - 3:35 PM
 * @project Thogakade
 */
public class CustomerController implements CustomerService {

    //Customer Ids Combo Box Load
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> ids = new ArrayList<>();

        while (resultSet.next()) {
            ids.add(
                    resultSet.getString(1)
            );
        }
        return ids;
    }


    //Save Customer
    @Override
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO Customer VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1,customer.getCustomerId());
        preparedStatement.setObject(2,customer.getName());
        preparedStatement.setObject(3,customer.getAddress());
        preparedStatement.setObject(4,customer.getSalary());

        return preparedStatement.executeUpdate()>0;
    }

    //Update Customer
    @Override
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "UPDATE Customer SET name=?,address=?,salary=? WHERE customerId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1,customer.getName());
        preparedStatement.setObject(2,customer.getAddress());
        preparedStatement.setObject(3,customer.getSalary());
        preparedStatement.setObject(4,customer.getCustomerId());

        return preparedStatement.executeUpdate()>0;
    }

    //Delete Customer
    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        if (DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE customerId='" + id + "'").executeUpdate()>0){
             return true;
        }else {
             return false;
        }
    }

    //Use For Load Combo Box Data To Text Field
    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer WHERE customerId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)

            );

        } else {
            return null;
        }
    }

    //Load Table To Customer
    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Customer> customers = new ArrayList<>();

        while (resultSet.next()){
            customers.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));

        }
        return customers;
    }
}
