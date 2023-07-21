package Main;
import java.util.Date;

import javax.swing.JRadioButton;


public class CustomRadioButton extends JRadioButton {
    private Date customValue;

    public CustomRadioButton(String text, Date customValue) {
        super(text);
        this.customValue = customValue;
    }

    public Date getCustomValue() {
        return customValue;
    }

    public void setCustomValue(Date customValue) {
        this.customValue = customValue;
    }
}
