package pl.karol.lingu;

import org.springframework.stereotype.Component;

@Component
public class UpperCaseDecorator implements TextDecorator {
    @Override
    public String decorate(String text) {
        return text.toUpperCase();
    }
}
