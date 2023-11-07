package authortitle;

public class AuthorTitle {
    private int atAuthorID;
    private int atISBN;

    public AuthorTitle(int atAuthorID, int atISBN) {
        this.atAuthorID = atAuthorID;
        this.atISBN = atISBN;
    }
    public void setAtAuthorID(int atAuthorID) {
        this.atAuthorID = atAuthorID;
    }
    public void setAtISBN(int atISBN) {
        this.atISBN = atISBN;
    }
    public int getAtAuthorID() {
        return atAuthorID;
    }
    public int getAtISBN() {
        return atISBN;
    }
}