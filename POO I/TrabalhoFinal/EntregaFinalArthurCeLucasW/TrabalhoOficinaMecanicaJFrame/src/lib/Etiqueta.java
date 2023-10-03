package lib;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Etiqueta extends JLabel{    
    public Etiqueta (String mensagem, int x, int y, int width, int height, int letterSize, int alinhamento){
        super(mensagem);
        setBounds(x, y, width, height);
        setFont(new Font("Arial", Font.BOLD, letterSize));
        setHorizontalAlignment(alinhamento);
    }

    
    public void setCor(Color color, Color background) {
        setForeground(color); //cor da fonte
        setBackground(background);
    }

}
