package proyecto.ponti.CONLAB.config;

import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.Field;
import java.util.*;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/*VA AYUDAR A CAPTURAR TODOS LOS ERRORES*/
public class GlobalEXceptionHandle {
    private final MessageSource messageSource;

    public GlobalEXceptionHandle(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @ExceptionHandler
    ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exception){
        Map<String, Object> errorDetail=new HashMap<>();
        errorDetail.put("title","Error de validacion");
        errorDetail.put("code","invalid_form");
        errorDetail.put("status", UNPROCESSABLE_ENTITY.value());

        List<String> errors=new ArrayList<>();

        for(FieldError fieldError:exception.getBindingResult().getFieldErrors()){
            String message=messageSource.getMessage(fieldError, Locale.getDefault());
            errors.add(message);
        }
        errorDetail.put("errors",errors);
        return new ResponseEntity<>(errorDetail,UNPROCESSABLE_ENTITY);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityActionVetoException.class)
    void handlerEntityNotFoundException(){
    }
}
