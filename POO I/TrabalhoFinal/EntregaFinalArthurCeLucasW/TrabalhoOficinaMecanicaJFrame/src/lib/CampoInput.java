package lib;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

public class CampoInput extends JTextField{
    
    public CampoInput(String mensagem, int x, int y, int width, int height, int letterSize){
        super(mensagem);
        setBounds(x, y, width, height); //x e y configuram a distancias das margens o restante o tamanho do botao
        setFont(new Font("Arial", Font.PLAIN, letterSize)); //configura a fonte
    }

    public void setCor(Color background, Color color) {
        setForeground(color); //cor da fonte
        setBackground(background);
    }
}
