package voll.med.api.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratarErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarerro404 (){

        return  ResponseEntity.notFound().build();

    }

    public ResponseEntity tratarerro400 (MethodArgumentNotValidException ex ){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new).toList());
    }

    private record DadosErrosValidacao (String campo, String mensagem){

        public DadosErrosValidacao (FieldError erro) {
            this(erro.getField(),erro.getDefaultMessage());
        }
    }


}
