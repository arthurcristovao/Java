package pacote;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("João", 23);

        new UsuarioDAO().criarUsuario(usuario);
        

        
    }
}
