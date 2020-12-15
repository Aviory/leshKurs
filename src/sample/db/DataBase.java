package sample.db;

import sample.objjects.Contact;

import java.sql.*;
import java.util.LinkedList;

public class DataBase {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/contacts?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private static final String table_name = "datacontacts";


    public static void add(Contact contact){
        // делаем sql запрос добавления данных

        try {

            // коннект с базой
            con = DriverManager.getConnection(url, user, password);
            // готовим запрос
            String query = " insert into "+table_name+" (name, number, company, description)"
                    + " values (?, ?, ?, ?)";

            // отправляем данные в запрос
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, contact.getNameText());
            preparedStmt.setString (2, contact.getNumberText());
            preparedStmt.setString   (3, contact.getCompanyText());
            preparedStmt.setString(4, contact.getDescriptionText());

            // отправляем сам запрос
            preparedStmt.execute();
            //обязательно закрываем коннект с базой
            con.close();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
    public static void update(Contact contact){
        try {

            // коннект с базой
            con = DriverManager.getConnection(url, user, password);

            // готовим запрос апдейта
            String query = "update "+table_name+" set name = ?,number = ?, company = ?, description = ? where id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, contact.getNameText());
            preparedStmt.setString(2, contact.getNumberText());
            preparedStmt.setString(3, contact.getCompanyText());
            preparedStmt.setString(4, contact.getDescriptionText());
            preparedStmt.setInt(5, contact.getId());

            // отправляем сам запрос
            preparedStmt.executeUpdate();
            //обязательно закрываем коннект с базой
            con.close();
        }catch (Exception e){}
    }
    public static void delete(Contact contact){
        // коннект с базой
        try {
            con = DriverManager.getConnection(url, user, password);

            // готовим запрос апдейта
            String query = "DELETE FROM "+table_name+" WHERE id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, contact.getId());

            // отправляем сам запрос
            preparedStmt.executeUpdate();
            //обязательно закрываем коннект с базой
            con.close();
        }catch (Exception e){}
    }
    public static LinkedList<Contact> get(){
        LinkedList<Contact> contacts = new LinkedList<>();
        //готовим запрос
        String query= "select * from "+table_name;
        try {

            // коннект
            con = DriverManager.getConnection(url, user, password);
            // обьект запроса
            stmt = con.createStatement();

            // отправляем запрос
            rs = stmt.executeQuery(query);

            while (rs.next()) {//забираем данные построчно
                contacts.add(new Contact(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                System.out.println(contacts.getLast().getNameText());
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return contacts;
    }
}
