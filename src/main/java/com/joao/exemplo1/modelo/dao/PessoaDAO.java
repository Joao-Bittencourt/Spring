/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joao.exemplo1.modelo.dao;

import br.edu.ifrs.restinga.prog3.jezer.exemplo1.modelo.entidade.Pessoa;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

/**
 *
 * @author jezer
 
@Component
public class PessoaDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    protected Pessoa criaObjeto(SqlRowSet rowSet) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(rowSet.getInt("id"));
        pessoa.setNome(rowSet.getString("nome"));
        pessoa.setPeso(rowSet.getInt("peso"));
        pessoa.setAltura(rowSet.getFloat("altura"));
        pessoa.setEmail(rowSet.getString("email"));
        SqlRowSet telefoneRowSet = jdbcTemplate.queryForRowSet(
                "SELECT numero FROM pessoa_telefone WHERE pessoa_id=?",
                pessoa.getId());
        ArrayList<String> telefones = new ArrayList<>();
        while (telefoneRowSet.next()) {            
            telefones.add(telefoneRowSet.getString("numero"));
        }
        pessoa.setTelefones(telefones.toArray( new String[]{}));
        /*
        List<String> telefones = jdbcTemplate.queryForList(
                    "SELECT numero FROM pessoa_telefone WHERE pessoa_id=?",
                    String.class,   pessoa.getId());
        
        return pessoa;
    }

    public Pessoa inserir(Pessoa pessoa) {
        
        jdbcTemplate.update("INSERT INTO pessoa(nome, email, peso, altura)"
                + " VALUES (?,?,?,?)",
                pessoa.getNome(), pessoa.getEmail(),
                pessoa.getPeso(), pessoa.getAltura());
        int id = jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()", Integer.class);
        if(pessoa.getTelefones()!=null) {
            for (String telefone : pessoa.getTelefones()) {
                jdbcTemplate.update("INSERT INTO pessoa_telefone(pessoa_id,numero) "+
                        "VALUES(?,?)",id,telefone);
            }
        }
        pessoa.setId(id);
        return pessoa;
    }

    public ArrayList<Pessoa> listar() {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        SqlRowSet rowSetPessoas = jdbcTemplate.queryForRowSet(
                "SELECT * FROM pessoa");
        while (rowSetPessoas.next()) {
            Pessoa pessoa = criaObjeto(rowSetPessoas);
            pessoas.add(pessoa);
        }
        return pessoas;
    }
   

    public Pessoa recuperar(int id) {
        SqlRowSet rowSetPessoa = jdbcTemplate.queryForRowSet("SELECT * FROM pessoa WHERE id=?", id);
        if (rowSetPessoa.next()) {
            return criaObjeto(rowSetPessoa);
        } else 
            return null;

    }

    public void atualizar(Pessoa pessoa) {
        jdbcTemplate.update("UPDATE pessoa SET nome=?, peso=?, altura=?, email=? WHERE id=?",
                pessoa.getNome(), pessoa.getPeso(), pessoa.getAltura(), pessoa.getEmail(), pessoa.getId());
        
        jdbcTemplate.update("DELETE FROM pessoa_telefone WHERE pessoa_id=?", pessoa.getId());
        if (pessoa.getTelefones() != null) {
            for (String telefone : pessoa.getTelefones()) {
                jdbcTemplate.update("INSERT INTO pessoa_telefone(pessoa_id,numero) "
                        + "VALUES(?,?)", pessoa.getId(), telefone);
            }
        }
    }

    public void excluir(int id) {
        jdbcTemplate.update("DELETE FROM pessoa WHERE id=?", id);

    }
     public ArrayList<Pessoa> listar(int q) {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        SqlRowSet rowSetPessoas = jdbcTemplate.queryForRowSet(
                "SELECT nome FROM funcionario");
        while (rowSetPessoas.next()) {
            Pessoa pessoa = criaObjetu(rowSetPessoas);
            pessoas.add(pessoa);
        }
        return pessoas;
    }
     protected Pessoa criaObjetu(SqlRowSet rowSet) {
        Pessoa pessoa = new Pessoa();
       // pessoa.setId(rowSet.getInt("id"));
        pessoa.setNome(rowSet.getString("nome"));
        //pessoa.setPeso(rowSet.getInt("peso"));
        //pessoa.setAltura(rowSet.getFloat("altura"));
        //pessoa.setEmail(rowSet.getString("email"));
       // SqlRowSet telefoneRowSet = jdbcTemplate.queryForRowSet(
            //    "SELECT numero FROM pessoa_telefone WHERE pessoa_id=?",
           ///     pessoa.getId());
       // ArrayList<String> telefones = new ArrayList<>();
      //  while (telefoneRowSet.next()) {            
       //     telefones.add(telefoneRowSet.getString("numero"));
       // }
       // pessoa.setTelefones(telefones.toArray( new String[]{}));
        /*
        List<String> telefones = jdbcTemplate.queryForList(
                    "SELECT numero FROM pessoa_telefone WHERE pessoa_id=?",
                    String.class,   pessoa.getId());
        
        return pessoa;
    }
    */
@Component
public class PessoaDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private Pessoa  CriaObjeto(SqlRowSet rowSet){
           Pessoa p =new Pessoa();
           p.setNome(rowSet.getString("nome"));
           p.setID(rowSet.getInt("id"));
           p.setEmail(rowSet.getString("email"));
           p.setPeso( (int)rowSet.getLong("peso"));
           p.setAltura(rowSet.getFloat("altura"));
           //p.setTelefone();
    return p;
    
    
    }
    public Pessoa inserir(Pessoa pessoa){
     jdbcTemplate.update("INSERT INTO pessoa(nome,email,peso,altura)VALUES(?,?,?,?)",
       pessoa.getNome(),pessoa.getEmail(),pessoa.getPeso(),pessoa.getAltura() );                
      int ID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",Integer.class);
      if(pessoa.getTelefone()!=null){
          for(String telefone :pessoa.getTelefone()){
          jdbcTemplate.update("INSERT INTO pessoa_telefone(pessoa_id,numero) VALUES(?,?)",ID,telefone);
          }
      }     
      pessoa.setID(ID);
      return pessoa;      
    }
     public ArrayList<Pessoa> listar(){
        ArrayList<Pessoa> ListaPessoab =new ArrayList<>();
       SqlRowSet rowSetPessoa = jdbcTemplate.queryForRowSet("select * from pessoa");
         jdbcTemplate.execute("select * from pessoa");
        while(rowSetPessoa.next()){
          
           ListaPessoab.add( CriaObjeto(rowSetPessoa));
        }
         return ListaPessoab;
     }
     public Pessoa recuperar(int ID){
       
       SqlRowSet rowSetPessoa = jdbcTemplate.queryForRowSet("SELECT * FROM pessoa WHERE id = ?",ID);
        if(rowSetPessoa.next()){
         
         return  CriaObjeto(rowSetPessoa);
        }
       return null; 
     }
     
    public void  atualizar(Pessoa pessoa){
     jdbcTemplate.update("UPDATE pessoa SET nome=?,email=?,peso=?,altura=? WHERE id=?",
             pessoa.getNome(),pessoa.getEmail(),pessoa.getPeso(),pessoa.getAltura(),pessoa.getID());    
                   
      int ID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",Integer.class);
      pessoa.setID(ID);
        
    }
   
     public void excluir(int id){
        jdbcTemplate.update("DELETE FROM pessoa where ID =?",id);
    
     }
}
}
