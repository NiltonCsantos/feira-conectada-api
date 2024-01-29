package org.feiraconectada.feiraconectadaapi.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailService {



    @Value("${support.mail}")
    private String supportEmail;

    private  final JavaMailSender mailSender;

    private  final TemplateEngine templateEngine;

    public MailService( JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender=mailSender;
        this.templateEngine= templateEngine;
    }

    public void sendMail(String email, String fullName){

        System.out.println(supportEmail);
        System.out.println(email);


       try {

           final MimeMessage mimeMessage= this.mailSender.createMimeMessage();

           final MimeMessageHelper emailHelper;

           emailHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");

           emailHelper.setTo(email);
           emailHelper.setSubject("Boas vindas");
           emailHelper.setFrom(supportEmail);

           final Context context= new Context();

           context.setVariable("fullName", fullName);

           final String htmlContent= this.templateEngine.process("email", context);

           emailHelper.setText(htmlContent, true);


//           ClassPathResource classPathResourceImage= new ClassPathResource("static/images/welcomme1.png");
//
//
//           emailHelper.addInline("background", classPathResourceImage, "image/png");

           mailSender.send(mimeMessage);




       }catch (MailException e){
           System.out.println(e);
       } catch (MessagingException e) {
           throw new RuntimeException(e);
       }
    }
}
