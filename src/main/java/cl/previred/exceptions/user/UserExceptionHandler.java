package cl.previred.exceptions.user;

import cl.previred.exceptions.ErrorResponse;
import cl.previred.exceptions.user.custom.UserExistHandler;
import cl.previred.exceptions.user.custom.UserNotExistHandler;
import cl.previred.messages.UserMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(basePackages = "cl.previred.user")
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        String field = ex.getFieldErrors().get(0).getField();
        //List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse((HttpStatus) status,field+": "+errorMessage);
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(UserNotExistHandler.class)
    public ResponseEntity<Object> userNotExist(HttpServletRequest req, UserNotExistHandler ex){
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, UserMessage.USER_NOT_FOUND));
    }

    @ExceptionHandler(UserExistHandler.class)
    public ResponseEntity<Object> userExist(HttpServletRequest req, UserExistHandler ex){
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, UserMessage.USER_FOUND));
    }

   /* @ExceptionHandler(UserWrongOldPassHandler.class)
    public ResponseEntity<Object> wrongPassHandler(HttpServletRequest req, UserWrongOldPassHandler ex){
        return buildResponseEntity(new ErrorResponse(HttpStatus.UNAUTHORIZED, UserMessage.WRONG_PASS));
    }*/
    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse){
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
    }
}
