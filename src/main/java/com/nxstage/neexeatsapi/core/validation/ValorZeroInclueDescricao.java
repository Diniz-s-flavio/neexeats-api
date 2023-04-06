package com.nxstage.neexeatsapi.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {ValorZeroIncluiDescricaoValidator.class}
)
public @interface ValorZeroInclueDescricao {
    String message() default "Sem descriação brigatoria";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String valorField();
    String descricaoField();
    String descricaoObrigatoria();
}
