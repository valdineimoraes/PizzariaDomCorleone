package Model;

import java.io.Serializable;

/**
 * Created by valdinei on 09/11/17.
 */

public class Aluno implements Serializable{
    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private String responsavel;
    private int turma;

    public Aluno() {
    }

    public Aluno(int id, String nome, String telefone, String endereco, String responsavel, int turma) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.turma = turma;
    }


    public Aluno(String nome, String telefone, String endereco, String responsavel, int turma) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.turma = turma;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o){
        return this.id == ((Aluno)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}
