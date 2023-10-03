package lib;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class Lista extends JList<String>{

     public Lista(DefaultListModel<String> model, int x, int y, int width, int height, int letterSize) {
        super(model);
        setBounds(x, y, width, height);
        setFont(new Font("Arial", Font.BOLD, letterSize));
        setCellRenderer(new CentralizaConteudoDaListaNoCentroHorizontal());
    }

    public void setCor(Color color, Color background) {
        setForeground(color); // cor da fonte
        setBackground(background);
    }
}
