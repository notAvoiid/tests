package com.abreu.tests.exceptions;

import com.abreu.tests.exceptions.handler.ApiExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static com.abreu.tests.utils.ProductConstants.EMAIL_ALREADY_EXISTS;
import static com.abreu.tests.utils.ProductConstants.PRODUCT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
public class ApiExceptionHandlerTests {

    @InjectMocks
    private ApiExceptionHandler exceptionHandler;
    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
    }

    @Nested
    class NotFoundException {

        @Test
        @DisplayName("Handle Entity Not Found Exception")
        void handleEntityNotFoundException() {
            var response = exceptionHandler.handleEntityNotFoundException(
                    new ProductNotFoundException(PRODUCT_NOT_FOUND),
                    request
            );

            assertNotNull(response);
            assertNotNull(response.getBody());
            assertNotNull(response.getBody().getTimestamp());
            assertNotNull(response.getBody().getMethod());
            assertNotNull(response.getBody().getPath());

            assertEquals(NOT_FOUND, response.getStatusCode());
            assertEquals(request.getMethod(), response.getBody().getMethod());
            assertEquals(request.getRequestURI(), response.getBody().getPath());

            assertEquals(ResponseEntity.class, response.getClass());
            assertEquals(ErrorMessage.class, response.getBody().getClass());
            assertEquals(PRODUCT_NOT_FOUND, response.getBody().getMessage());
            assertEquals(404, response.getBody().getStatus());
        }

        @Test
        @DisplayName("Handle Null Request When Not Found")
        void handleNullRequestNotFound() {
            var response = exceptionHandler.handleEntityNotFoundException(
                    new ProductNotFoundException(PRODUCT_NOT_FOUND),
                    null
            );

            assertNotNull(response);
            assertEquals(NOT_FOUND, response.getStatusCode());
        }

    }
}
