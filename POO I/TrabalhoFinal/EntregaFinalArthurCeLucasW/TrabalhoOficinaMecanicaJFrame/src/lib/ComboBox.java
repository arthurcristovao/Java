package lib;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.Font;

public class ComboBox extends JComboBox<String>{
    public ComboBox(DefaultComboBoxModel<String> model, int x, int y, int width, int height, int letterSize) {
        super(model);
        setBounds(x, y, width, height);
        setFont(new Font("Arial", Font.BOLD, letterSize));
        setRenderer(new CentralizaConteudoDaListaNoCentroHorizontal());
    }
}
