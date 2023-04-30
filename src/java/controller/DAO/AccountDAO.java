/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import model.Account;

/**
 *
 * @author Mosena
 */
public class AccountDAO extends MyDAO {
    RoleDAO rd=new RoleDAO();
    public Account findAccount(String username){
        query="Select * from Account where UserName=?";
        Account a=null;
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            rs=ps.executeQuery();
            if(rs.next()){
                String email=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                a=new Account(rs.getString("UserName"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email, phonenumber,rs.getString("FirstName"),
                        rs.getString("LastName"));           
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return a;
    }
    
    public ArrayList<Account> findAccountByUsername(String username, int pageNo){
        query="Select * from Account where UserName like ? order by UserName offset ? rows fetch next ? rows only";
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, "%"+username+"%");
            ps.setInt(2, (pageNo-1)*10);
            ps.setInt(3, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("UserName"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email, phonenumber,rs.getString("FirstName"),rs.getString("LastName"));   
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByUsername(String username){
        query="Select * from Account where UserName like ?";
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, "%"+username+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("UserName"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email, phonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));   
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByFirstName(String firstName){
        query="Select * from Account where FirstName like ?";
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, "%"+firstName+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("UserName"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email, phonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));   
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByFirstName(String firstName,int pageNo){
        query="Select * from Account where FirstName like ? order by FirstName "
                + "offset ? rows fetch next ? rows only";
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, "%"+firstName+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("UserName"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email, phonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));   
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByLastName(String lastName){
        query="Select * from Account where LastName like ?";
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, "%"+lastName+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("UserName"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email, phonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));   
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByLastName(String lastName,int pageNo){
        query="Select * from Account where LastName like ? order by LastName "
                + "offset ? rows fetch next ? rows only";
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, "%"+lastName+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("UserName"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email, phonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));   
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByEmail(String email, int pageNo){
        if(email.equals("empty")){
            query="Select * from Account where Email is null "
                    + "order by UserName offset ? rows fetch next ? rows only";
        }
        else{
            query="Select * from Account where Email like ? "
                    + "order by UserName offset ? rows fetch next ? rows only";
        }
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            if(!email.equals("empty")){
                ps.setString(1,"%"+email+"%");
                ps.setInt(2, (pageNo-1)*10);
                ps.setInt(3, 10);
            }
            else{
                ps.setInt(1, (pageNo-1)*10);
                ps.setInt(2, 10);
            }
            rs=ps.executeQuery();
            while(rs.next()){
                String tempEmail=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(tempEmail==null){
                    tempEmail="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("UserName"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        tempEmail,phonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByEmail(String email){
        if(email.equals("empty")){
            query="Select * from Account where Email is null "
                    + "order by UserName offset ? rows fetch next ? rows only";
        }
        else{
            query="Select * from Account where Email like ? "
                    + "order by UserName offset ? rows fetch next ? rows only";
        }
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            if(!email.equals("empty")){
                ps.setString(1,"%"+email+"%");
            }
            rs=ps.executeQuery();
            while(rs.next()){
                String tempEmail=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(tempEmail==null){
                    tempEmail="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("UserName"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        tempEmail,phonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByRole(int role){
        query="Select * from Account where RoleID=?";
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, role);
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("Username"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email, phonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByRole(int role,int pageNo){
        query="Select * from Account where RoleID=? "
                + "order by UserName offset ? rows fetch next ? rows only";
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, role);
            ps.setInt(2, (pageNo-1)*10);
            ps.setInt(3, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("Username"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email, phonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByPhonenumber(String phonenumber, int pageNo){
        if(phonenumber.equals("empty")){
            query="Select * from Account where PhoneNumber is null "
                    + "order by UserName offset ? rows fetch next ? rows only";
        }
        else{
            query="Select * from Account where PhoneNumber=? "
                    + "order by UserName offset ? rows fetch next ? rows only";
        }
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            if(!phonenumber.equals("empty")){
                ps.setString(1, phonenumber);   
                ps.setInt(2, (pageNo-1)*10);
                ps.setInt(3, pageNo);
            }
            else{
                ps.setInt(1, (pageNo-1)*10);
                ps.setInt(2, pageNo);
            }
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String tempPhonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(tempPhonenumber==null){
                    tempPhonenumber="empty";
                }
                Account a=new Account(rs.getString("Username"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email,tempPhonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));   
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public ArrayList<Account> findAccountByPhonenumber(String phonenumber){
        if(phonenumber.equals("empty")){
            query="Select * from Account where PhoneNumber is null";
        }
        else{
            query="Select * from Account where PhoneNumber=?";
        }
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            if(!phonenumber.equals("empty")){
                ps.setString(1, phonenumber);   
            }
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String tempPhonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(tempPhonenumber==null){
                    tempPhonenumber="empty";
                }
                Account a=new Account(rs.getString("Username"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email,tempPhonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));   
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accounts;
    }
    
    public boolean deleteAccount(String username){
        query="Delete from Account where UserName=?";
        try {
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            int rows=ps.executeUpdate();
            if(rows==0){
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    public boolean addAccount(String username,String password,int roleId
            ,String email,String phonenumber,String firstName,String lastName){
        query="insert into Account values(?,?,?,?,?,?,?)";
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, roleId);
            ps.setString(4, email);
            ps.setString(5, phonenumber);
            ps.setString(6, firstName);
            ps.setString(7, lastName);
            ps.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(AccountDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean updateAccount(String username,String password,
            String email,String phonenumber,String firstName, String lastName){
        query="update Account set Password=?, Email=?, Phonenumber=?, "
                + "FirstName=?, LastName=?"
                + " where Username=?";
        try{
            ps=con.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, email);
            ps.setString(3, phonenumber);
            ps.setString(4, firstName);
            ps.setString(5, lastName);
            ps.setString(6, username);
            int rows=ps.executeUpdate();
            if(rows==0){
                return false;
            }
        }
        catch(SQLException ex){
            return false;
        }
        finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkPassword(Account a,String password){
        return a.getPassword().equals(password);
    }
    
    public boolean checkRole(Account a){
        return a.getRole().getPermissionLevel()!=2;
    }
    
    public boolean checkAdmin(Account a){
        return a.getRole().getPermissionLevel()<2;
    }
    
    public void changeRole(String username, int roleId){
        query="update Account set RoleID = ? where UserName= ?";
        try{
            ps=con.prepareStatement(query);
            ps.setInt(1, roleId);
            ps.setString(2, username);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean samePassword(String password, String rePassword){
        return password.equals(rePassword);
    }
    
    public boolean validateUsername(String username){
        return findAccount(username)==null;
    }
    
    public boolean validatePassword(String password){
        String regex="[A-Za-z0-9.,?!@#$%]{8,}";
        return Pattern.matches(regex, password);
    }
    
    public boolean validateEmail(String email){
        String regex="^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email) || email.isEmpty();
    }
    public boolean validatePhonenumber(String number){
        String regex="[0-9]{10,15}";
        return Pattern.matches(regex, number) || number.isEmpty();
    }
    
    public int countAccounts(){
        query="select count(*) from Account";
        try{
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }
    public ArrayList<Account> getAccounts(int pageNo){
        query="select * from Account order by UserName offset ? rows fetch next ? rows only";
        ArrayList<Account> accounts=new ArrayList<>();
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, (pageNo-1)*10);
            ps.setInt(2, 10);
            rs=ps.executeQuery();
            while(rs.next()){
                String email=rs.getString("Email");
                String phonenumber=rs.getString("PhoneNumber");
                if(email==null){
                    email="empty";
                }
                if(phonenumber==null){
                    phonenumber="empty";
                }
                Account a=new Account(rs.getString("UserName"),
                        rs.getString("Password"),
                        rd.findRole(rs.getInt("RoleID")),
                        email, phonenumber,
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return accounts;
    }
}
