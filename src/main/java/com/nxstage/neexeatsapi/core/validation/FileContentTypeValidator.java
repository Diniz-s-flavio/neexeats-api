package com.nxstage.neexeatsapi.core.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private String[] allows;
    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allows = constraintAnnotation.allowed();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext constraintValidatorContext) {
       boolean valid = false;
        for (String allow : this.allows) {
            if (allow.equals(value.getContentType())){
                valid = true;
                break;
            }
        }

       return valid;
    }
}
