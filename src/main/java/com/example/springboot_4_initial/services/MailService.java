package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.services.interfaces.IMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

@Service
public class MailService implements IMailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${url.front}")
    String url_front;

    @Async
    @Override
    public void send_mail_confirm_account_reclutador(String to, String subject, String name_user, String url, int randome_number) {
        Context context = new Context();
        context.setVariable("user_name", name_user);
        context.setVariable("url", ("http://localhost:4200/confirm/recruiter" + "/" + url));
        context.setVariable("randome_number", randome_number);

        String html_content = templateEngine.process("confirm_account_reclutador", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html_content, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Override
    public void send_mail_confirm_account_candidate(String to, String subject, String name_candidate, String url, int randome_number) {
        Context context = new Context();
        context.setVariable("name_candidate", name_candidate);
        context.setVariable("url", ("http://localhost:4200/confirm/candidate" + "/" + url));
        context.setVariable("randome_number", randome_number);

        String html_content = templateEngine.process("confirm_account_candidate", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html_content, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void sendMailUpdateApplication(String to, String subject, String nameCandidate, String nameVacancy, String status, String lastUpdate) {
        Context context = new Context();
        context.setVariable("nameCandidate", nameCandidate);
        context.setVariable("status", status);
        context.setVariable("lastUpdate", lastUpdate);
        context.setVariable("nameVacancy", nameVacancy);

        String htmlContent = templateEngine.process("updateApplication", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMailDeleteApplication(String to, String subject, String nameCandidate, String nameVacancy, String status, String lastUpdate) {
        Context context = new Context();
        context.setVariable("nameCandidate", nameCandidate);
        context.setVariable("status", status);
        context.setVariable("lastUpdate", lastUpdate);
        context.setVariable("nameVacancy", nameVacancy);

        String htmlContent = templateEngine.process("deleteApplication", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMailResetPasswordCandidate(String to, String subject, String token, String randomNumber) {
        Context context = new Context();
        context.setVariable("token", token);
        context.setVariable("randomNumber", randomNumber);

        String htmlContent = templateEngine.process("forgetPasswordCandidate", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMailResetPasswordRecruiter(String to, String subject, String token, String randomNumber) {
        Context context = new Context();
        context.setVariable("token", token);
        context.setVariable("randomNumber", randomNumber);

        String htmlContent = templateEngine.process("forgetPasswordRecruiter", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMailToCandidate(String to, String subject, String nameCandidate, String nameVancacy, String body) {
        Context context = new Context();
        context.setVariable("nameCandidate", nameCandidate);
        context.setVariable("nameVancacy", nameVancacy);
        context.setVariable("body", body);

        String htmlContent = templateEngine.process("mailToCandidate", context);
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
