package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
public class FactorialNumber {
   public FactorialNumber(){}
   private Double factorial_num;
}
