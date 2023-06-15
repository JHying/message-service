package tw.hyin.demo.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tw.hyin.java.utils.Log;
import tw.hyin.java.utils.http.ResponseObj;

import javax.security.auth.message.AuthException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YingHan 2022
 */
@ControllerAdvice
@SuppressWarnings("rawtypes")
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 統一處理 @valid 產生之例外事件
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        Log.error(ex.getLocalizedMessage());

        ResponseObj apiError = new ResponseObj<>(HttpStatus.BAD_REQUEST, errors, null);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    /**
     * 處理常見的 Bad Request 例外事件
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add("Bad Request");
        errors.add(ex.getMessage());
        Log.error(ex.getMessage());
        ResponseObj apiError = new ResponseObj<>(HttpStatus.BAD_REQUEST, errors, null);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

    /**
     * 處理常見的 Bad Request 例外事件
     */
    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add("Bad Request");
        errors.add(ex.getMessage());
        Log.error(ex.getMessage());
        ResponseObj apiError = new ResponseObj<>(HttpStatus.BAD_REQUEST, errors, null);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add("Request Conflict");
        errors.add(ex.getMessage());
        Log.error(ex.getMessage());
        ResponseObj apiError = new ResponseObj<>(HttpStatus.CONFLICT, errors, null);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

    /**
     * 統一處理 Exception 型態之例外
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler({Exception.class, RuntimeException.class, IOException.class})
    @ResponseBody
    public ResponseEntity<ResponseObj> handleResultException(Exception e) {
        List<String> errors = new ArrayList<>();
        errors.add("Internal Server Error");
        errors.add(e.getMessage());
        Log.error("發生錯誤：" + e.getMessage());
        return new ResponseEntity(ResponseObj.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                .errors(errors).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 統一處理 AuthException 型態之例外
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler({AuthException.class, AccessDeniedException.class})
    @ResponseBody
    public ResponseEntity<ResponseObj> handleAccessDeniedException(Exception e) {
        List<String> errors = new ArrayList<>();
        errors.add("Authentication Error");
        errors.add(e.getMessage());
        Log.error("認證錯誤：" + e.getMessage());
        return new ResponseEntity(ResponseObj.builder().status(HttpStatus.FORBIDDEN)
                .errors(errors).build(), HttpStatus.FORBIDDEN);
    }

}
