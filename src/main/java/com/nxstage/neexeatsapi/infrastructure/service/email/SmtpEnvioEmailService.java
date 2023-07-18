package com.nxstage.neexeatsapi.infrastructure.service.email;

import com.nxstage.neexeatsapi.core.email.EmailProperties;
import com.nxstage.neexeatsapi.domain.service.EnvioEmailService;
import com.nxstage.neexeatsapi.infrastructure.exception.EmailException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {
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
            helper.setTo(message.getDestinatarios().toArray(new String[0]));
            helper.setSubject(message.getAssunto());
            helper.setText(mailBody,true);

            System.out.println("Helper From email=" + mimeMessage.getFrom());

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não Foi Possível enviar o E-mail",e);
        }
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
