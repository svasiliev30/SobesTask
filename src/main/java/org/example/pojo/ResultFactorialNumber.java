package org.example.pojo;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ResultFactorialNumber {
    private Double result;
    private HttpStatus status;
}
