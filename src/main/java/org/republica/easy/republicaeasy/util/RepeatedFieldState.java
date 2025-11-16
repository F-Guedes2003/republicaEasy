package org.republica.easy.republicaeasy.util;

import java.util.List;

public class RepeatedFieldState implements ErrorMessageState {
    @Override
    public String generate(List<String> fields) {
        return "User already exists with the provided: " + String.join(", ", fields);
    }
}
