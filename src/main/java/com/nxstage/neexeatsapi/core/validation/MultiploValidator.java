package com.nxstage.neexeatsapi.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

    private int numeroMultiplo;
    @Override
    public void initialize(Multiplo constraintAnnotation) {
        this.numeroMultiplo =  constraintAnnotation.numero();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        if (number != null) {
            var valorDecimal = BigDecimal.valueOf(number.doubleValue());
            var multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo);
            var rest = valorDecimal.remainder(multiploDecimal);

            valid =  BigDecimal.ZERO.compareTo(rest) == 0;
        }

        return valid;
    }
}
