package easv.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.BE.User;
import easv.DAL.DataAccess.DataAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DalUser implements DaoUser {

    private final DataAccess dataAccess ;

    public DalUser(){
        dataAccess = new DataAccess();
    }
    @Override
    public List<User> getAllUsers() {
        ArrayList<User> customers = new ArrayList<>();

        try(Connection con = dataAccess.getConnection()){
            String sql = "SELECT * FROM users ";
            Statement statement = con.createStatement();
          ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String usertype = rs.getString("usertype");
                User customer = new User(id,username , email , usertype);
                customers.add(customer);
            }


        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<User> getAllAdmins() {
        ArrayList<User> customers = new ArrayList<>();

        try(Connection con = dataAccess.getConnection()){
            String sql = "SELECT * FROM users where usertype = 'Admin' ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String username = rs.getString("username");
                String email = rs.getString("email");
                User customer = new User(newestid(),username  , email , null);
                customers.add(customer);
            }


        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<User> getAllEventCoordinatoer() {
        ArrayList<User> customers = new ArrayList<>();

        try(Connection con = dataAccess.getConnection()){
            String sql = "SELECT * FROM users where usertype = 'EventCoordinator' ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String username = rs.getString("username");
                String pasword = rs.getString("password");
                String email = rs.getString("email");
                User customer = new User(newestid(),username  , email , null);
                customers.add(customer);
            }


        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public User adduser(String username, String password, String email , String usertype) {
        User user = null ;
        try (Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO users(username , password, email , usertype)" +
                    "VALUES  (?,?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, username);
            prs.setString(2, password);
            prs.setString(3, email);
            prs.setString(4, usertype);
            prs.executeUpdate();
            user = new User(newestid(),username, email , usertype);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int newestid() {
        int newid = -1;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM users ORDER by id desc";
            PreparedStatement prs = con.prepareStatement(sql);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {

                newid = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newid;
    }

    @Override
    public void updateUser(User customer, String username, String password, String email , String usertype) {
        try(Connection con = dataAccess.getConnection()){
            String sql = "UPDATE users SET  username = ? , password = ? , email = ?  , usertype = ?  WHERE id = ? ";

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , username);
            prs.setString(2 , password);
            prs.setString(3, email);
            prs.setString(4,usertype);
            prs.setInt(5,customer.getId());

            prs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
    try(Connection con = dataAccess.getConnection()) {

        String sql = "DELETE FROM users WHERE Id = ?";
        PreparedStatement prs = con.prepareStatement(sql);
        prs.setInt(1 , user.getId());

        prs.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    @Override
    public User verifyUsers(String username, String password) {
        User user = null;

        try(Connection con = dataAccess.getConnection()){
            String sql = " SELECT * FROM users where users.username = ? AND password = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 ,username);
            prs.setString(2, password);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String usertype = rs.getString("usertype");
                user = new User(id,username , email , usertype);
            }
            return user;


        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> searchforUser(String quury) {

        List<User> users = new ArrayList<>();

        String stringquery = "%" + quury + "%" ;

        try(Connection con = dataAccess.getConnection()) {
            String command = "Select * from users where  username like ? or usertype like ? ";
            PreparedStatement prs = con.prepareStatement(command);
            prs.setString(1 ,stringquery);
            prs.setString(2,stringquery);
            prs.execute();
            ResultSet rs = prs.getResultSet();
            while(rs.next()){

                int id = rs.getInt("id");
               String username = rs.getString("username");
               String email = rs.getString("email");
               String usertype = rs.getString("usertype");

               User user = new User(id ,username,email,usertype);
               users.add(user);
            }

        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
