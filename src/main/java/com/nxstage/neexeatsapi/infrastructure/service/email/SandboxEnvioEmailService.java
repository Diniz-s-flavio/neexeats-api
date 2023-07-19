package com.nxstage.neexeatsapi.infrastructure.service.email;

import com.nxstage.neexeatsapi.core.email.EmailProperties;
import com.nxstage.neexeatsapi.domain.service.EnvioEmailService;
import com.nxstage.neexeatsapi.infrastructure.exception.EmailException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;


public class SandboxEnvioEmailService implements EnvioEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;
    @Autowired
    private Configuration freemakerConfig;

    @Override
    public void send(Mensagem message) {
        try {
            String mailBody = templateProcessor(message);
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"UTF-8");
            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(emailProperties.getSandbox().getTo());
            helper.setSubject(message.getAssunto());
            helper.setText(mailBody,true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não Foi Possível enviar o E-mail",e);
        }
        System.out.println("Destinatario " + emailProperties.getSandbox().getTo());
    }

    private String templateProcessor(Mensagem message){
        try {
            Template template = freemakerConfig.getTemplate(message.getCorpo());

            return FreeMarkerTemplateUtils
                    .processTemplateIntoString(template,message.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não Foi Possível montar o template do e-mail",e);
        }
    }

}
