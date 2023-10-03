package pessoa;

public enum ArCond {
    SIM("Sim"), NAO("Nao");
    private String arCond;

    ArCond(String arCond){
        this.arCond = arCond;
    }

    public static ArCond getSim() {
        return SIM;
    }

    public static ArCond getNao() {
        return NAO;
    }
}
