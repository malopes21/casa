
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jose.lemes
 */
public class ContaCorrenteController {
    ArrayList<ContaCorrenteModel> contas;
    
    ContaCorrenteView view;

    public ContaCorrenteController() {
        contas = new ArrayList<>();
        
        view = new ContaCorrenteView();
        view.setVisible(true);
        
        view.addNovaContaListener(new NovaContaListener());        
        view.addDepositarValorListener(new DepositarValorListener());
        view.addSacarValorListener(new SacarValorListener());
        view.addConsultaSaldoListener(new ConsultaSaldoListener());
        view.addExibeContasListener(new ExibeContasListener());
    }   
    
    private boolean cadastrarConta(ContaCorrenteModel conta){
        return contas.add(conta);
    }
    
    private ContaCorrenteModel buscaConta(int id) {
        for( ContaCorrenteModel c: contas) {
            if( c.getNumero() == id ) return c;
        }
        return null;
    }

    private class ConsultaSaldoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getContaConsultaId();
            ContaCorrenteModel conta = buscaConta(id);
            if( conta == null ){
                view.showMsg("Conta não encontrada.");                
            }
            else {
                view.showMsg(conta.getSaldo().toString());
            }
        }
    }

    private class SacarValorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idConta = view.getContaSaqueId();
            String senha = view.getContaSaqueSenha();
            BigDecimal valor= view.getContaSaqueValor();
            
            ContaCorrenteModel conta = buscaConta(idConta);
            if( conta == null){
                view.showMsg("Conta inexistente!");
                return;
            }
            else if( !conta.getSenha().equals(senha)) {                
                view.showMsg("Senha Incorreta!");                
            }
            else {
                if( conta.sacar(valor) ){
                    view.showMsg("Valor Sacado com Sucesso!");
                    view.exibeContas(contas);
                }
                else view.showMsg("Impossível realizar o saque!");
            }            
        }
    }

    private class DepositarValorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            int idConta = view.getContaDepositoId();
            BigDecimal valor = view.getContaDepositoValor();
            
            ContaCorrenteModel contaDeposito = buscaConta(idConta);
            if( contaDeposito != null ) {            
                if( contaDeposito.depositar(valor) ) {
                     view.showMsg("Valor Depositado com Sucesso!");
                     view.exibeContas(contas);
                }
                else{
                    view.showMsg("Erro ao depositar.");
                }
            }
            else view.showMsg("Conta não encontrada.");
            
        }        
    }

    private class ExibeContasListener implements ActionListener{        
        @Override
        public void actionPerformed(ActionEvent e) {
            view.exibeContas(contas);            
        }
    }

    private class NovaContaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ContaCorrenteModel novaConta = view.getNovaConta();
            if(novaConta!=null) {
                if( buscaConta(novaConta.getNumero())==null){
                    if( cadastrarConta(novaConta) ) view.showMsg("Conta Cadastrada!");  
                    view.exibeContas(contas);
                }
                else{
                    view.showMsg("Já existe uma conta com o número informado!");
                }
            }       
            else{
                view.showMsg("Erro ao cadastrar conta");
            }            
        }
    }    
}

