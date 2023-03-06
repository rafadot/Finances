package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.service.interfaces.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void forgetPassword(String to , int code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Redefinir senha");
        message.setText("Olá, foi feito uma solicitação de alteração de senha na sua conta, o seu código para verificação é : " + code);
        javaMailSender.send(message);
    }
}
