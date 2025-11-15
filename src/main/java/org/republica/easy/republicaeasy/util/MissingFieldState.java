package org.republica.easy.republicaeasy.util;

import java.util.List;

public class MissingFieldState implements ErrorMessageState {
    @Override
    public String generate(List<String> fields) {
        return "Missing fields: " + String.join(", ", fields);
    }
}
