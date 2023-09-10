package com.andersen.countrycity.validation;

import com.andersen.countrycity.exception.ValidationException;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final MessageSource messageSource;

    public void handleValidation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            fieldError -> resolveErrorMessage(fieldError)));
            throw new ValidationException(errors);
        }
    }

    private String resolveErrorMessage(FieldError fieldError) {
        return messageSource.getMessage(fieldError.getDefaultMessage(), null,
                fieldError.getDefaultMessage(), null);
    }
}
