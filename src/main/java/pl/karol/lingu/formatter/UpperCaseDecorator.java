package pl.karol.lingu.formatter;

import org.springframework.stereotype.Component;

@Component
public class UpperCaseDecorator implements TextDecorator {
    @Override
    public String decorate(String text) {
        return text.toUpperCase();
    }
}
