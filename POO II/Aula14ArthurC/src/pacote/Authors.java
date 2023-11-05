/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

/**
 *
 * @author 08220186
 */
public class Authors {
    private int authorsID;
    private String firstName;
    private String lastName;

    public Authors(int authorsID, String firstName, String lastName) {
        this.authorsID = authorsID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Authors(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    

    public int getAuthorsID() {
        return authorsID;
    }

    public void setAuthorsID(int authorsID) {
        this.authorsID = authorsID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
