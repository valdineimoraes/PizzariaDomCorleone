package Model;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

/**
 * Created by valdinei on 04/11/17.
 */

public class Usuario  {

    private int id;
    private String nome;
    private String usuario;
    private String senha;
    private String cargo;
    private String telefone;

    //Construtor para ser instanciada a classe em alguma outra classe do projeto
    public Usuario() {

    }

    public Usuario(int id, String nome, String usuario, String senha, String cargo, String telefone) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.cargo = cargo;
        this.telefone = telefone;
    }

    public Usuario(String nome, String usuario, String senha, String cargo, String telefone) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.cargo = cargo;
        this.telefone = telefone;
    }

    public int getId() {
        return this.id;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o){
        return this.id == ((Usuario)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }


}
