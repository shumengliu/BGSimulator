package gui.components;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class IntField extends TextField {
    public IntField() {
        setTextFormatter(new TextFormatter<>(c -> {
            if (c.isContentChange()) {
                if (c.getControlNewText().length() == 0) {
                    return c;
                }
                try {
                    Integer.parseInt(c.getControlNewText());
                    return c;
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return c;
        }));
    }
}
