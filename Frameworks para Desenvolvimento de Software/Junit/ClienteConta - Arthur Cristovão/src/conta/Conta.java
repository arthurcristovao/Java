package conta;

import cliente.Cliente;

public class Conta {
    private int idConta;
    private Cliente cliente;
    private Double saldo;
    private int limite;
    
    public Conta(int idConta, Cliente cliente, Double saldo, int limite) {
        super();
        this.idConta = idConta;
        this.cliente = cliente;
        this.saldo = saldo;
        this.limite = limite;
    }
    
    public Conta(Cliente cliente, Double saldo, int limite) {
        super();
        this.cliente = cliente;
        this.saldo = saldo;
        this.limite = limite;
    }
    

    public int getIdConta() {
		return idConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Double getSaldo() {
		return saldo;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	// Apenas valores positivos
    public boolean saca(Double valor) {
    	
    	if( validarPositivo(valor) && verificaSaldo(valor)) {
    		this.saldo -= valor;
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    // Deve receber um valor positivo
    public boolean deposita(Double valor) {
    	
    	if(validarPositivo(valor)) {
    		this.saldo += valor;
    		return true;
    	} else {
    		return false;
    	}
 
    }
    

    
    // Deve receber um objeto de conta não nulo
    public boolean transfere(Conta destino, Double valor) throws ContaException {
        validarContaNula(destino);
        validarPositivo(valor);
        verificaSaldo(valor);

        this.saldo -= valor;
        destino.saldo += valor;

        return true;
    }

    // O saldo não deve ficar negativo com um valor absoluto superior ao limite
    // (gera uma exceção não verificada do tipo ContaException, que deve ser criada)
    public boolean verificaSaldo(Double valor) {
    	if (this.saldo - valor < -this.limite) {
            throw new ContaException("Saldo negativo superior ao limite");

        }
        return true;
    }
    
    private boolean validarPositivo(Double valor) {
    	if(valor > 0) 
    		return true;
    	else
    		throw new ContaException("O valor deve ser positivo");
    }
    
    private boolean validarContaNula(Conta conta) {
    	
    	if(conta == null) 
    		throw new ContaException("A conta é nula!");
    	else 
    		return true;
    }
}
