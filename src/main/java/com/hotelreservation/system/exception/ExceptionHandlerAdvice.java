package com.hotelreservation.system.exception;

import com.hotelreservation.system.Result;
import com.hotelreservation.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    //tell spring that this exception is an exception handler
    //ceci appartient à l'entête
    //tell spring that this exception is an exception handler
    @ExceptionHandler({ObjectNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handleObjectNotFoundException(ObjectNotFoundException exception) {
        return new Result(false, StatusCode.NOT_FOUND, exception.getMessage());
    }

    /*
  return new Result ( flag: false, StatusCode. INVALID_ARGUMENT, message:
   */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Result handleValidationException(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());

        errors.forEach((error) -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });

        return new Result(false, StatusCode.INVALID_ARGUMENT, "Provided Arguments are invalid, see data for details. ", map);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    Result handleAccessDeniedException(AccessDeniedException exception) {
        return new Result(false, StatusCode.FORBIDDEN,"No permission", exception.getMessage());
    }

    /*Fallback gère toutes les exception non gérées*/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Result handleOtherException(Exception exception) {
        return new Result(false, StatusCode.INTERNAL_SERVER_ERROR,"A server internal error occurs", exception.getMessage());
    }

   /* Sécurity spring

   @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result handleAuthenticationException(Exception exception) {
        return new Result(false, StatusCode.UNAUTHORIZED,"username or password is incorrect", exception.getMessage());
    }

    @ExceptionHandler(AccountStatusException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result handleAccountStatusException(AccountStatusException exception) {

        return new Result(false, StatusCode.UNAUTHORIZED,"user account is abnormal", exception.getMessage());
    }

    @ExceptionHandler(InvalidBearerTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result handleInvalidBearerTokenException(InvalidBearerTokenException exception) {
        return new Result(false, StatusCode.UNAUTHORIZED,"The access token provided is expired revoked,malforme, or invalid for other reasons", exception.getMessage());
    }*/



  /* @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Result handleAccessDeniedException(TransactionSystemException exception) {
        return new Result(false, StatusCode.INVALID_ARGUMENT,"Provided arguments are invalid, see data for details", exception.getMessage());
    }

*/




}
