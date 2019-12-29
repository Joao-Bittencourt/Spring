package com.joao.exemplo1.modelo.dao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.joao.exemplo1.modelo.entidade.Pessoa;

     
@Component
public class PessoaDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private Pessoa  CriaObjeto(SqlRowSet rowSet){
           Pessoa p =new Pessoa();
           p.setNome(rowSet.getString("nome"));
           p.setId(rowSet.getInt("id"));
           //p.setEmail(rowSet.getString("email"));
           p.setPeso( (int)rowSet.getLong("peso"));
           p.setAltura(rowSet.getFloat("altura"));
           //p.setTelefone();
    return p;
    
    
    }
    public Pessoa inserir(Pessoa pessoa){
     jdbcTemplate.update("INSERT INTO pessoa(nome,email,peso,altura)VALUES(?,?,?,?)",
       pessoa.getNome(),pessoa.getEmail(),pessoa.getPeso(),pessoa.getAltura() );                
      int ID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",Integer.class);
     
      pessoa.setId(ID);
      return pessoa;      
    }
     public ArrayList<Pessoa> listar( ){
        ArrayList<Pessoa> ListaPessoab =new ArrayList<>();
       SqlRowSet rowSetPessoa = jdbcTemplate.queryForRowSet("select * from pessoa");
         jdbcTemplate.execute("select * from pessoa");
        while(rowSetPessoa.next()){
          
           ListaPessoab.add( CriaObjeto(rowSetPessoa));
        }
         return ListaPessoab;
     }
     public Pessoa recuperar(int ID){
       
       SqlRowSet rowSetPessoa = jdbcTemplate.queryForRowSet("SELECT * FROM funcionario WHERE id = ?",ID);
        if(rowSetPessoa.next()){
         
         return  CriaObjeto(rowSetPessoa);
        }
       return null; 
     }
     
    public void  atualizar(Pessoa pessoa){
     jdbcTemplate.update("UPDATE pessoa SET nome=?,email=?,peso=?,altura=? WHERE id=?",
             pessoa.getNome(),pessoa.getEmail(),pessoa.getPeso(),pessoa.getAltura(),pessoa.getId());    
                   
      int ID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",Integer.class);
      pessoa.setId(ID);
        
    }
   
     public void excluir(int id){
        jdbcTemplate.update("DELETE FROM pessoa where ID =?",id);
    
     }
     public ArrayList<Pessoa> listar(int a) {
         ArrayList<Pessoa> pessoas = new ArrayList<>();
         SqlRowSet rowSetPessoas = jdbcTemplate.queryForRowSet(
                 "SELECT nome FROM funcionario");
         while (rowSetPessoas.next()) {
             Pessoa pessoa = CriaObjeto(rowSetPessoas);
             pessoas.add(pessoa);
         }
         return pessoas;
     }
}

