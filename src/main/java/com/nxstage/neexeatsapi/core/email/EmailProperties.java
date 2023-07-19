package com.nxstage.neexeatsapi.core.email;

import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Data
@Validated
@Component
@ConfigurationProperties("neexeat.email")
public class EmailProperties {
    @NotNull
    private String remetente;
    private Sandbox sandbox = new Sandbox();
    @NonNull
    private MailSenderType impl = MailSenderType.FAKE;

    public enum MailSenderType {
        FAKE,SMTP,SANDBOX;
        }
    @Data
    public class Sandbox {
        private String to;
    }
    }
