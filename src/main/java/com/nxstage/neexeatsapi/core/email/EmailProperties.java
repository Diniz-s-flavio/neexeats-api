package com.nxstage.neexeatsapi.core.email;

import lombok.Data;
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
}
