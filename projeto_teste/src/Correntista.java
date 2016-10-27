/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jose.lemes
 */
class Correntista {
    
    int id;
    String nome;   
    

    public Correntista() {
    }

    public Correntista(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }       

    @Override
    public String toString() {
        return "Correntista{" + "id=" + id + ", nome=" + nome + '}';
    }
    
    
    
}
