package controller;

import db.DBConnection;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created By Ravindu Prathibha
 * @created 1/15/2024 - 3:59 PM
 * @project Thogakade
 */
public class ItemController {

    //Item Ids Combo Box Load
    public List<String> getItemIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Item";
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


    //Use For Load Combo Box Data To TextField
    public Item getItem(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Item WHERE code=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)

            );

        } else {
            return null;
        }
    }


}
