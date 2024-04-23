package org.example.check;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckImplTest {

    private CheckImpl checkImpl = new CheckImpl();
    @Test
    void FactorialIsLessThanNull() throws Exception {
        Double factorial = -5.;
        Assertions.assertThrows(IllegalArgumentException.class, () ->checkImpl.getCheck(factorial));
    }

    @Test
    void FactorialIsMoreThan100() throws Exception {
        Double factorial = 101.;
        Assertions.assertThrows(IllegalArgumentException.class, () ->checkImpl.getCheck(factorial));
    }

    @Test
    void FactorialIsNull() throws Exception {
        Double factorial = null;
        Assertions.assertThrows(NullPointerException.class, () ->checkImpl.getCheck(factorial));
    }

    @Test
    void FactorialGoodCheck() throws Exception {
        Double factorial = 50.;
        Assertions.assertEquals(true,checkImpl.getCheck(factorial));
    }

}