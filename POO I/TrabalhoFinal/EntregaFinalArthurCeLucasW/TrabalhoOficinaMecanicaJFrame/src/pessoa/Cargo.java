package pessoa;

public enum Cargo {
    MECANICO("Mecânico"), ATENDENTE("Atendente");
    private String cargo;
    
    Cargo(String cargo){
        this.cargo = cargo;
    }

    public static Cargo getMECANICO() {
        return MECANICO;
    }

    public static Cargo getATENDENTE() {
        return ATENDENTE;
    }
    
}
