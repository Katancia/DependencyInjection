package pl.karol.lingu;

import org.springframework.stereotype.Component;

@Component
public class NormalCaseDecorator implements TextDecorator {

    @Override
    public String decorate(String text) {
        return text;
    }
}
