package com.wliafe.common.config.mail;

import com.wliafe.common.config.my.UimConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class MailConfig {
    @Autowired
    private UimConfig uimConfig;
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    public void sendMailForActivationAccount(String activationUrl, String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setSubject(uimConfig.getTitle());
            message.setFrom(mailUsername);
            message.setTo(email);
            message.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("activationUrl", activationUrl);
            String text = templateEngine.process("active.html", context);
            message.setText(text, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
