package uz.pdp.warehouserestapp.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        return new ResponseEntity<>(new ApiError(INTERNAL_SERVER_ERROR, "Server error", e.getLocalizedMessage()), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException e, WebRequest request) {
        return new ResponseEntity<>(new ApiError(NOT_FOUND, "Not Found", e.getLocalizedMessage()), NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequestException e, WebRequest request) {
        return new ResponseEntity<>(new ApiError(BAD_REQUEST, "Bad Request", e.getLocalizedMessage()), NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    protected ResponseEntity<Object> handleConflict(ConflictException e, WebRequest request) {
        return new ResponseEntity<>(new ApiError(CONFLICT, "Data integrity violation", e.getLocalizedMessage()), CONFLICT);
    }

    /**
     * Customize the response for MethodArgumentNotValidException.
     * <p>This method delegates to {@link #handleExceptionInternal}.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ObjectError allError : ex.getBindingResult().getAllErrors()) {
            errors.add(allError.getDefaultMessage());
        }
        return new ResponseEntity<>(new ApiError(BAD_REQUEST, "Validation Failed", errors), BAD_REQUEST);
    }

    /**
     * Customize the response for HttpRequestMethodNotSupportedException.
     * <p>This method logs a warning, sets the "Allow" header, and delegates to
     * {@link #handleExceptionInternal}.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        Objects.requireNonNull(ex.getSupportedHttpMethods()).forEach(method -> builder.append(method).append(" "));
        return new ResponseEntity<>(new ApiError(METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString().trim()), METHOD_NOT_ALLOWED);
    }
}
