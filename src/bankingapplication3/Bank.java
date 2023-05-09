/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingapplication3;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Bank {
    private String name;

    public Bank(String name) {
        this.name = name;
    }
    public void openAccount(Account acc){
        Connection con = BankConnection.connect();
        String sql = "insert into account (accID,accName,accBalance) " 
                    + "values(?,?,?)" ;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, acc.getNumber());
            preparedStatement.setString(2, acc.getName());
            preparedStatement.setDouble(3, acc.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void listAccount(){
         Connection con = BankConnection.connect();
        try {
            // order a command through statement
            Statement statement= con.createStatement();
            String sql = "select * from account";
            
            // keep a result in object of ResultSet , we must iterrate through this object
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                System.out.println("[ " + result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + " ]");
            
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    public void closeAccount(int accNumber){
        Connection con = BankConnection.connect();
        String sql = "delete from account where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, accNumber);
            preparedStatement.executeUpdate();
             
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
    }
    
    public void depositMoney(Account acc , double amount){
        acc.deposit(amount);
        Connection con = BankConnection.connect();
        String sql = "update account set accBalance = ? where accID = ?" ;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, acc.getBalance());
            preparedStatement.setInt(2, acc.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void withdrawMoney(Account acc , double amount){
        acc.withdraw(amount);
        Connection con = BankConnection.connect();
        String sql = "update account set accBalance = ? where accID = ?" ;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, acc.getBalance());
            preparedStatement.setInt(2, acc.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Account getAccount(int number){
        int checkNum;
        String name = "";
        double balance = 0;
        
        Connection con = BankConnection.connect();
        String sql = "select * from account where accID =  ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, number);
            ResultSet result = preparedStatement.executeQuery();
            
            // must looping into row , then get data form it
            while(result.next()){
            // get value follow index of colume
            name = result.getString(2);
            balance = result.getDouble(3);
            checkNum = result.getInt(1);
            if(checkNum == number) break;
            }

            return new Account(number , name , balance);
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
      
           
    return null;
        
    }
}
