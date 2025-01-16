package com.tutorial.tutorial_webflux.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new HashMap<>();
        Throwable error = super.getError(request);

        if (error instanceof CustomException) {
            CustomException customException = (CustomException) error;
            errorAttributes.put("error", customException.getMessage());
            errorAttributes.put("status", customException.getHttpStatus().value());
        }

        return errorAttributes;
    }
}
