package com.akshay.automationexecrices.utils;

import com.akshay.automationexecrices.base.BaseTest;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.util.Properties;

public class MailUtils extends BaseTest{

    public static void sendEmailWithPdfReport(String pdfPath) {
        final String fromEmail = util.getProperty("senderEmail"); // your email
        final String password = util.getProperty("appPassword"); // use App Password for Gmail
        final String toEmail = util.getProperty("receiverEmail"); // recipient email
        final String subject = "📄 Automation Test Report (PDF)";


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject(subject);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached PDF automation test report.");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(pdfPath));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("✅ Email sent with PDF report.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

