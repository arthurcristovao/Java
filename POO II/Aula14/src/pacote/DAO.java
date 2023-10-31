/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pacote;

import java.util.ArrayList;

/**
 *
 * @author 08220186
 */
public interface DAO {
    public int insert ( Authors a );
    public Authors read( int id );
    public ArrayList<Authors> list();
    public int update( Authors a );
    public int delete( int id );
}
