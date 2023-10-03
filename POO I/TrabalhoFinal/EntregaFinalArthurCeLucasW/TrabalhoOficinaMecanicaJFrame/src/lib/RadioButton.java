package lib;
import java.awt.Font;
import javax.swing.JRadioButton;
 
public class RadioButton extends JRadioButton {
    public RadioButton(String mensagem, int x, int y, int width, int height, int letterSize) {
        super(mensagem);
        setBounds(x, y, width, height);
        setFont(new Font("Arial", Font.BOLD, letterSize));
        // Outras inicializações personalizadas, se necessário
    }

}

