/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

/**
 *
 * @author 08220186
 */
public class Aula14 {
    public static void main(String[] args) {
        AuthorsDAO authorsConnection = new AuthorsDAO();
        
        Authors a2 = new Authors("Raphael", "Tavares");
        
        authorsConnection.insert(a2);
        
        a2 = authorsConnection.read(1);

        if (a2 != null) {
            System.out.println(a2.getFirstName());
        } else {
            System.out.println("Não foi possível encontrar o autor.");
        }
    }
}
