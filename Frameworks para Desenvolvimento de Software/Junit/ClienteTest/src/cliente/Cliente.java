package cliente;

import java.time.LocalDate;

public class Cliente {
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;

    public Cliente(String cpf, String nome, LocalDate dataNascimento) throws ClienteException {
        super();
        
        validarAtributos(cpf, nome, dataNascimento);
        
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    private void validarAtributos(String cpf, String nome, LocalDate dataNascimento) throws ClienteException {
        
    	if (!validarCPF(cpf)) 
            throw new ClienteException("CPF inválido. O CPF deve estar no formato XXX.YYY.ZZZ-WW. Apenas numeros. ");
        
        if (!validarNome(nome)) 
            throw new ClienteException("Nome inválido. O nome não deve conter caracteres não alfabéticos.");  
        
        if (!validarAno(dataNascimento.getYear())) 
            throw new ClienteException("Data de nascimento inválida. Deve ser maior que 1900 e menor que a data atual.");
        
    }

    private boolean validarCPF(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }
    
    private boolean validarNome(String nome) {
        return nome.matches("[a-zA-Z]+");
    }
    
    private boolean validarAno(int ano) {
        int anoAtual = LocalDate.now().getYear();
        return ano > 1900 && ano < anoAtual;
    }
}
