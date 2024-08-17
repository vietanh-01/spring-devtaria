package com.spring.identity_service.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // if there're fields which are null in json -> not show
public class ApiResponse<T> { //
    @Builder.Default
    private int code = 1000;
    private String message;
    private T result;
}
