package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

public class MessageSenderService {
    @Autowired
    JavaMailSender emailSender;

    public void sendMessage(String email, String message) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(email);
            helper.setText(message, true);
            helper.setSubject("Test send HTML email");
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println(e.getFailedMessage());
            System.out.println("Can not send message");
        } catch (javax.mail.MessagingException e) {
            System.out.println(e.getMessage());
            System.out.println("Can not create MimeMessage");
        }
    }
}
