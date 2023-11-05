/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

/**
 *
 * @author Arthur
 */
public class Titles {
    private int ISBN;
    private String title;
    private String editionNumber;
    private String copyright;

    public Titles(int ISBN, String title, String editionNumber, String copyright) {
        this.ISBN = ISBN;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
    }
    
    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
