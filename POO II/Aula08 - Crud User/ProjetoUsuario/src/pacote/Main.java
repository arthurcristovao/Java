package pacote;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Jo�o", 23);

        new UsuarioDAO().criarUsuario(usuario);
        

        
    }
}
