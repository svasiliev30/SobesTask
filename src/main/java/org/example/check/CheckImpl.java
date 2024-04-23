package org.example.check;

import org.springframework.stereotype.Component;

@Component("CheckImpl")
public class CheckImpl implements Check {
    @Override
    public boolean getCheck(Double data) {
        if (data.equals(null))throw new NullPointerException("you passed null");
        if (data < 0 || data > 100) throw new IllegalArgumentException("you passed an incorrect value");
        return true;
    }
}
