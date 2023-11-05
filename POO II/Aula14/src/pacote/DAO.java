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
interface DAO<T> {
    void insert(T t);
    T read(int id);
    void update(T t);
    void delete(int id);
    ArrayList<T> list();
}