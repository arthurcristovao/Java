package lib;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Botao extends JButton implements ActionListener{
    public Botao (String mensagem, int x, int y, int width, int height, int letterSize){
        super(mensagem);
        setBounds(x, y, width, height); //x e y configuram a distancias das margens o restante o tamanho do botao
        setFont(new Font("Arial", Font.BOLD, letterSize)); //configura a fonte
        addActionListener(this); //Ação do botão
    }
    
    public Botao (String mensagem, int x, int y, int width, int height, int letterSize, Color color, Color background){
        super(mensagem);
        setBounds(x, y, width, height); //x e y configuram a distancias das margens o restante o tamanho do botao
        setFont(new Font("Arial", Font.BOLD, letterSize)); //configura a fonte
        addActionListener(this); //Ação do botão
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Botão pressionado: " + getText());
    }

    
    public void setCor(Color color, Color background) {
        setForeground(color); //cor da fonte
        setBackground(background);
    }
}
