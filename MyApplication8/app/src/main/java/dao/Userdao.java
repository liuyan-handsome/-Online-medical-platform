package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;
import utils.JDBCUtils;

public class Userdao {
    public boolean login(String name,String password){

        String sql = "select * from users where name = ? and password = ?";

        JDBCUtils.getConn();

        try {
            PreparedStatement pst=JDBCUtils.connection.prepareStatement(sql);

            pst.setString(1,name);
            pst.setString(2,password);

            if(pst.executeQuery().next()){

                return true;

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(JDBCUtils.connection);
        }

        return false;
    }

    public boolean register(User user){

        String sql = "insert into users(user,password) values (?,?)";

        JDBCUtils.getConn();

        try {
            PreparedStatement pst=JDBCUtils.connection.prepareStatement(sql);

            pst.setString(1,user.getUsername());
            pst.setString(2,user.getUsername());

            int value = pst.executeUpdate();

            if(value>0){
                return true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(JDBCUtils.connection);
        }
        return false;
    }

    public User findUser(String name){

        String sql = "select * from users where name = ?";

        JDBCUtils.getConn();
        User user = null;
        try {
            PreparedStatement pst=JDBCUtils.connection.prepareStatement(sql);

            pst.setString(1,name);

            ResultSet rs = pst.executeQuery();

            while (rs.next()){

                int id = rs.getInt(0);
                String namedb = rs.getString(1);
                String username = rs.getString(2);
                String passworddb  = rs.getString(3);
                int age = rs.getInt(4);
                String phone = rs.getString(5);
                user = new User(username,passworddb);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(JDBCUtils.connection);
        }

        return user;
    }
}
