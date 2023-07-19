package com.nxstage.neexeatsapi.core.email;

import com.nxstage.neexeatsapi.core.email.EmailProperties.MailSenderType;
import com.nxstage.neexeatsapi.domain.service.EnvioEmailService;
import com.nxstage.neexeatsapi.infrastructure.service.email.FakeEnvioEmailService;
import com.nxstage.neexeatsapi.infrastructure.service.email.SandboxEnvioEmailService;
import com.nxstage.neexeatsapi.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService(){
        if(MailSenderType.SMTP.equals(emailProperties.getImpl())){
            return new SmtpEnvioEmailService();
        }else if(MailSenderType.SANDBOX.equals(emailProperties.getImpl())){
            return new SandboxEnvioEmailService();
        }else {
            return new FakeEnvioEmailService();
        }
    }
}
