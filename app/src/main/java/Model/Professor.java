package Model;

/**
 * Created by valdinei on 09/11/17.
 */

public class Professor {

    private int id;
    private String nome;
    private String telefone;
    private String formacao;

    public Professor() {
    }

    public Professor(int id, String nome, String telefone, String formacao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.formacao = formacao;
    }

    public Professor(String nome, String telefone, String formacao) {
        this.nome = nome;
        this.telefone = telefone;
        this.formacao = formacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    @Override
    public String toString() {
        return nome;
    }
}
