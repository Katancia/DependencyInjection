package pl.karol.lingu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConsoleOutputWriter {

    private TextDecorator textDecorator;

    @Autowired
    public ConsoleOutputWriter(@Qualifier("upperCaseDecorator") TextDecorator textDecorator) {
        this.textDecorator = textDecorator;
    }

    public void println(String text) {
        System.out.println(textDecorator.decorate(text));
    }
}
