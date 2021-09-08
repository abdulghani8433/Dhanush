package com;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sendMessage=null;
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/jdbc?"
                            + "user=root & password=");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from jdbctable");
            sendMessage= writeResultSet(resultSet);
            System.out.println("message"+sendMessage);



        } catch (Exception e) {
            throw e;
        } finally {

        }

        String to = "abdulghani8433@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "abdulghani8433@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("abdulghani8433@gmail.com", "Abdul@1234");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            //  message.setText(sendMessage);

            message.setContent(sendMessage,"text/html");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }


    public static String writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set

        StringBuffer stBuffer=new StringBuffer();
        stBuffer.append("<table><tr><th>stid</th><th>stdname</th><th>stdemail</th><th>stdno</th></tr>");
        while (resultSet.next()) {
            
            stBuffer.append("<tr>");
            stBuffer.append("<td>" +resultSet.getString("sname")+"</td>");
            stBuffer.append("<td>" +resultSet.getString("salary")+"</td>");
            stBuffer.append("<td>" +resultSet.getString("company")+"</td>");
            stBuffer.append("<td>" +resultSet.getString("date")+"</td>");
            stBuffer.append("</tr>");
        }
        stBuffer.append("</table>");
        return stBuffer.toString();
    }



}




