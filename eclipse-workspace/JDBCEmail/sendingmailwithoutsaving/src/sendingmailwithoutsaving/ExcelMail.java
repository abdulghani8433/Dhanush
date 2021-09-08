package sendingmailwithoutsaving;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;


public class ExcelMail {
		    public static void main(String[]args){
		try{
		String filename="data.xls";
		HSSFWorkbook hwb=new HSSFWorkbook();
		HSSFSheet sheet =  hwb.createSheet("new sheet");

		HSSFRow rowhead=   sheet.createRow((short)0);
		rowhead.createCell((short) 0).setCellValue("sname");
		rowhead.createCell((short) 1).setCellValue("salary");
		rowhead.createCell((short) 2).setCellValue("company");
		rowhead.createCell((short) 3).setCellValue("date");
		
		String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
         String username = "root";
         String password = "";
         Connection connection=DriverManager.getConnection(jdbcURL,username,password);

		Statement st=connection.createStatement();
		ResultSet rs=st.executeQuery("Select * from jdbctable");
		int i=1;
		while(rs.next()){
		HSSFRow row=   sheet.createRow((short)i);
		row.createCell((short) 0).setCellValue(rs.getString("sname"));
		row.createCell((short) 1).setCellValue(rs.getString("salary"));
		row.createCell((short) 2).setCellValue(rs.getString("company"));
		row.createCell((short) 3).setCellValue(rs.getString("date"));
		i++;
		}
		FileOutputStream fileOut =  new FileOutputStream(filename);
		hwb.write(fileOut);
		fileOut.close();
		System.out.println("Your excel file has been generated!");

		} catch ( Exception ex ) {
		    System.out.println(ex);

		}
		
		  String to = "abdulghani8433@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "vamsichegondi@gmail.com";

	      final String username = "vamsichegondi@gmail.com";//change accordingly
	      final String password = "VAMSICHE";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Testing Subject");

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("This is message body");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "data.xls";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");     
	      }
	      catch (MessagingException e)
	      {
	         throw new RuntimeException(e);
	      }
     }
}