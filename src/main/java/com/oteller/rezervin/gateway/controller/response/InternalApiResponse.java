package com.oteller.rezervin.gateway.controller.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternalApiResponse<T> {
    private FriendlyMessage friendlyMessage;
    private T payload;
    private boolean hasError;
    private Map<String,String> errorMessages;
    private HttpStatus httpStatus;
}
