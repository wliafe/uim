package com.wliafe.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 邮件发送服务
 *
 * @author wliafe
 * @date 2023/1/12 0:17
 **/
@Service
public class MailService {
    @Autowired
    private UimService uimService;
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    public void sendMailForActivationAccount(String code, String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setSubject(uimService.getTitle());
            message.setFrom(mailUsername);
            message.setTo(email);
            message.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("code", code);
            String text = templateEngine.process("active.html", context);
            message.setText(text, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
