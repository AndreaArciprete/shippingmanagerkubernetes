package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Exception;

import com.google.gson.Gson;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility.ErrorRequest;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility.ErrorResponse;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;

@Component
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${KAFKA_TOPIC_LOGGING}")
    private String topic_logging;

    @Autowired
    private KafkaTemplate<String,String> template;

    public void sendMessageInvoicing(String key, String value){
        template.send(topic_logging,key,value);
    }


    @ExceptionHandler(ShippingNotFoundException.class)
    public ResponseEntity<Object> shippingNotFound(HttpServletRequest req, ShippingNotFoundException ex) throws IOException {
        return sendMessageLogging40x(req,HttpStatus.NOT_FOUND,ex);
    }

    @ExceptionHandler(PageException.class)
    public ResponseEntity<Object> pageLessThanZero(HttpServletRequest req, PageException ex) throws IOException {
        return sendMessageLogging40x(req,HttpStatus.BAD_REQUEST,ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgument(HttpServletRequest req, IllegalArgumentException ex) throws IOException {
        return sendMessageLogging50x(req,HttpStatus.INTERNAL_SERVER_ERROR,ex);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Object> missingRequestHeader(HttpServletRequest req, MissingRequestHeaderException ex) throws IOException {
        return sendMessageLogging40x(req,HttpStatus.BAD_REQUEST,ex);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> pathMismatch(HttpServletRequest req, NoHandlerFoundException ex) throws IOException {
        return sendMessageLogging40x(req,HttpStatus.NOT_FOUND,ex);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> numberFormatError(HttpServletRequest req, NumberFormatException ex) throws IOException {
        return sendMessageLogging40x(req,HttpStatus.BAD_REQUEST,ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> requestMethodNotSupport(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) throws IOException {
        return sendMessageLogging40x(req,HttpStatus.METHOD_NOT_ALLOWED,ex);
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    public ResponseEntity<Object> conversionNotSupported(HttpServletRequest req, ConversionNotSupportedException ex) throws IOException {
        return sendMessageLogging50x(req,HttpStatus.INTERNAL_SERVER_ERROR,ex);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> serviceUnavailable(HttpServletRequest req, ServiceUnavailableException ex) throws IOException {
        return sendMessageLogging50x(req,HttpStatus.SERVICE_UNAVAILABLE,ex);
    }

    @ExceptionHandler(ExecutionControl.NotImplementedException.class)
    public ResponseEntity<Object> notImplemented(HttpServletRequest req, ExecutionControl.NotImplementedException ex) throws IOException {
        return sendMessageLogging50x(req,HttpStatus.NOT_IMPLEMENTED,ex);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) throws IOException {
        return sendMessageLogging40x(req,HttpStatus.BAD_REQUEST,ex);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> missingServletRequestParameter(HttpServletRequest req, MissingServletRequestParameterException ex) throws IOException {
        return sendMessageLogging40x(req,HttpStatus.BAD_REQUEST,ex);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<Object> typeMismath(HttpServletRequest req, TypeMismatchException ex) throws IOException {
        return sendMessageLogging40x(req,HttpStatus.BAD_REQUEST,ex);
    }


    public ResponseEntity<Object> sendMessageLogging40x(HttpServletRequest req, HttpStatus status, Exception ex){
        ErrorResponse e = new ErrorResponse(Instant.now().toString(),status.value(),ex.getMessage().toString(),req.getRequestURL().toString()+ '-'+ req.getMethod());
        ErrorRequest error_request = new ErrorRequest(Instant.now().toString(), req.getRemoteAddr(),"shipping",
                req.getRequestURL().toString()+ '-'+ req.getMethod(),String.valueOf(e.getStatus()));
        String errore = new Gson().toJson(error_request);
        sendMessageInvoicing("http_errors",errore);
        return new ResponseEntity<>(e, status);
    }

    public ResponseEntity<Object> sendMessageLogging50x(HttpServletRequest req, HttpStatus status, Exception ex){
        ErrorResponse e = new ErrorResponse(Instant.now().toString(),status.value(),ex.getMessage().toString(),req.getRequestURL().toString()+ '-'+ req.getMethod());
        ErrorRequest error_request = new ErrorRequest(Instant.now().toString(), req.getRemoteAddr(),"shipping",
                req.getRequestURL().toString()+ '-'+ req.getMethod(),ex.getStackTrace().toString());
        String errore = new Gson().toJson(error_request);
        sendMessageInvoicing("http_errors",errore);
        return new ResponseEntity<>(e, status);
    }


}
