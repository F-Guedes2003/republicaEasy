package org.republica.easy.republicaeasy.util;

import java.util.List;

public class FieldErrorMessageGenerator {
    private ErrorMessageState state;

    public FieldErrorMessageGenerator() {
        this.state = new MissingFieldState();
    }

    public FieldErrorMessageGenerator(ErrorMessageState state) {
        this.state = state;
    }

    public void setState(ErrorMessageState state) {
        this.state = state;
    }

    public String generateMessage(List<String> fields) {
        return state.generate(fields);
    }
}
