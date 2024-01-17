package controller;

import db.DBConnection;
import model.ItemDetails;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Created By Ravindu Prathibha
 * @created 1/16/2024 - 4:08 PM
 * @project Thogakade
 */
public class OrderController {

    public String getOrderId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM `Order` ORDER BY orderId DESC LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId = tempId+1;

            if (tempId < 9) {
                return "0-00" + tempId;
            } else if (tempId < 99) {
                return "0-0" + tempId;
            } else {
                return "0-" + tempId;
            }
        } else {
            return "0-001";
        }

    }



    //Save Order
    public boolean placeOrder(Order order) {
        Connection connection=null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO `Order` VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, order.getOrderID());
            preparedStatement.setObject(2, order.getCustomerId());
            preparedStatement.setObject(3, order.getOrderDate());
            preparedStatement.setObject(4, order.getOrderTime());
            preparedStatement.setObject(5, order.getCost());

            if (preparedStatement.executeUpdate() > 0){
              if (saveOrderDetail(order.getOrderID(),order.getItemDetails())){
                  connection.commit();
                  return true;
              }else {
                  connection.rollback();
                  return false;
              }

            }else {
                connection.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    //Save OrderDetail
    private boolean saveOrderDetail(String orderId, ArrayList<ItemDetails>itemDetails) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp : itemDetails
        ) {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO OrderDetail VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, temp.getItemCode());
            preparedStatement.setObject(2, orderId);
            preparedStatement.setObject(3, temp.getQtyForSell());
            preparedStatement.setObject(4, temp.getUnitPrice());

            if (preparedStatement.executeUpdate() > 0) {

                if (updateQty(temp.getItemCode(),temp.getQtyForSell())){

                }else {
                    return false;
                }

            } else {
                return false;
            }

        }
        return true;
    }


    private boolean updateQty(String itemCode,int qty) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "UPDATE item SET qtyOnHand=(qtyOnHand-"+qty+") WHERE code='"+itemCode+"'";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeUpdate()>0;

    }
}
