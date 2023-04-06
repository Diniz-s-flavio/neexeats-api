package com.nxstage.neexeatsapi.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements
        ConstraintValidator<ValorZeroInclueDescricao,Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;
    @Override
    public void initialize(ValorZeroInclueDescricao constraint) {
        this.valorField = constraint.valorField();
        this.descricaoField = constraint.descricaoField();
        this.descricaoObrigatoria = constraint.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object objetoValidacao, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        try {
            BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField)
                    .getReadMethod().invoke(objetoValidacao);

            String descricao =  (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), descricaoField)
                    .getReadMethod().invoke(objetoValidacao);

            if (value != null && BigDecimal.ZERO.compareTo(value) == 0 && descricao != null) {
                valid =  descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }

            return valid;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
