/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author mk
 */
public interface DAO {
    
    public boolean inserir (Object obj);
    
    public boolean excluir (Object obj);
    
    public Object pesquisar (Object obj);
    
    public boolean editar (Object obj);
    
}
