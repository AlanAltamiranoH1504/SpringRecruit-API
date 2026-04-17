package com.example.springboot_4_initial.services.interfaces;

import java.time.LocalDateTime;

public interface IMailService {
    public abstract void send_mail_confirm_account_reclutador(String to, String subject, String name_user, String url, int randome_number);
    public abstract void send_mail_confirm_account_candidate(String to, String subject, String name_candidate, String url, int randome_number);
    public abstract void sendMailUpdateApplication(String to, String subject, String nameCandidate, String nameVacancy, String status, String lastUpdate);
    public abstract void sendMailDeleteApplication(String to, String subject, String nameCandidate, String nameVacancy, String status, String lastUpdate);
    public abstract void sendMailResetPasswordCandidate(String to, String subject, String token, String randomNumber);
    public abstract void sendMailResetPasswordRecruiter(String to, String subject, String token, String randomNumber);
    public abstract void sendMailToCandidate(String to, String subject, String nameCandidate, String nameVacancy, String body);
}
