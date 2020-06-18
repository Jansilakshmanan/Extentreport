package UtilityLayer;
import org.testng.annotations.Test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Emailablereport {

    Properties prop;
    Session session;
    String from=null;
    String[] to=null;
    String[] cc=null;
    public Emailablereport()
    {
        try {
            String configfilepath = "C:\\Selenium_projects\\ExtentReport\\src\\main\\java\\UtilityLayer\\Config.properties";
            FileInputStream ip = new FileInputStream(configfilepath);
            prop=new Properties();
            prop.load(ip);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    public void init() {
        from = prop.getProperty("frommailid");
        to = prop.getProperty("tomailids").split(";");
        cc = prop.getProperty("ccmailids").split(";");

         prop.setProperty("mail.smtp.host","smtp.gmail.com");
        prop.setProperty("mail.smtp.socketFactory.port","465");
        prop.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.starttls","true");


         session= Session.getDefaultInstance(prop,new javax.mail.Authenticator(){
             protected  PasswordAuthentication getPasswordAuthentication()
             {
                 return new PasswordAuthentication("*****", "*****");
             }
         });


    }

    public void sendemailwithattachment(String Extentreportpath)  {
        init();
        Message msg=new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));

            for(int i=0;i<to.length;i++)
            {
                System.out.println("Recepient_to mail ids are:" + to[i].toString());
                msg.addRecipient(Message.RecipientType.TO,new InternetAddress(to[i]));
            }
            for(int i=0;i<cc.length;i++)
            {
                System.out.println("Recepient_to mail ids are:" + cc[i].toString());
                msg.addRecipient(Message.RecipientType.CC,new InternetAddress(cc[i]));
            }

            msg.setSubject(" Basic Extent report");
            BodyPart msgbodypart1=new MimeBodyPart();
            msgbodypart1.setText("Extent Report for Gettitle");
            BodyPart msgbodypart2=new MimeBodyPart();

            String filename = "C:\\Selenium_projects\\ExtentReport\\test-output\\Extentreport.html";

            DataSource source1= (DataSource) new FileDataSource(Extentreportpath);


            msgbodypart2.setDataHandler(new DataHandler((javax.activation.DataSource) source1));
            msgbodypart2.setFileName(Extentreportpath);

            Multipart multipart =new MimeMultipart();
            multipart.addBodyPart(msgbodypart1);
            multipart.addBodyPart(msgbodypart2);
            msg.setContent(multipart);
            Transport.send(msg);

            System.out.println("Message sent succesfully");

        }
        catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
