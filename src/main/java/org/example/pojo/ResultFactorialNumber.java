package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ResultFactorialNumber {
    private Double result;
    private HttpStatus status;
}
