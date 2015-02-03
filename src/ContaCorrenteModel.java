
import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jose.lemes
 */
public class ContaCorrenteModel {
    
    private int numero;
    private String senha;
    private Agencia agencia;
    private Correntista correntista;
    private BigDecimal saldo;
    
    public ContaCorrenteModel() {        
        this.saldo = new BigDecimal("0.00");
    }

    public ContaCorrenteModel(int numero, String senha, Agencia agencia, Correntista correntista) {
        this.numero = numero;
        this.senha = senha;
        this.agencia = agencia;
        this.correntista = correntista;
        this.saldo = new BigDecimal("0.00");
    }
    
    
    
    public boolean sacar(BigDecimal valor) {
        if( valor.doubleValue() > 0) {
            if( valor.compareTo(saldo) == 1 ) {
                return false;
            }
            else {
                saldo = saldo.subtract(valor).setScale(2, RoundingMode.FLOOR);
                return true;
            }
        }
        return false;
    }
    
    public boolean depositar(BigDecimal valor) {   
        if( valor.doubleValue() > 0 ){
            saldo = saldo.add(valor).setScale(2, RoundingMode.FLOOR); 
            return true;
        }
        return false;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Correntista getCorrentista() {
        return correntista;
    }

    public void setCorrentista(Correntista correntista) {
        this.correntista = correntista;
    }    

    @Override
    public String toString() {
        return "Conta Corrente{" + "numero=" + numero + ", senha=" + senha + ", agencia=" + agencia + ", correntista=" + correntista + ", saldo=" + saldo + '}';
    }

    public BigDecimal getSaldo() {
        return saldo;
    }   
    
    
}
