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
           p.setId(rowSet.getInt("funcionario_id"));
           //p.setEmail(rowSet.getString("email"));
           p.setSalario( (int)rowSet.getDouble("salario"));
           p.setSexo(rowSet.getString("sexo"));
           //p.setTelefone();
    return p;
    
    
    }
    public Pessoa inserir(Pessoa pessoa){
     jdbcTemplate.update("INSERT INTO funcionario(nome,salario,nascimento,sexo)VALUES(?,?,?,?)",
       pessoa.getNome(),pessoa.getSalario(),1992-03-29,pessoa.getSexo());                
      int ID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",Integer.class);
     
      pessoa.setId(ID);
      return pessoa;      
    }
     public ArrayList<Pessoa> listar( ){
        ArrayList<Pessoa> ListaPessoab =new ArrayList<>();
       SqlRowSet rowSetPessoa = jdbcTemplate.queryForRowSet("select * from funcionario");
         jdbcTemplate.execute("select * from funcionario");
        while(rowSetPessoa.next()){
          
           ListaPessoab.add( CriaObjeto(rowSetPessoa));
        }
         return ListaPessoab;
     }
     public Pessoa recuperar(int ID){
       
       SqlRowSet rowSetPessoa = jdbcTemplate.queryForRowSet("SELECT * FROM funcionario WHERE funcionario_id = ?",ID);
        if(rowSetPessoa.next()){
         
         return  CriaObjeto(rowSetPessoa);
        }
       return null; 
     }
     
    public void  atualizar(Pessoa pessoa){
     jdbcTemplate.update("UPDATE funcionario SET nome=?,salario=?, sexo=? WHERE funcionario_id=?",
             pessoa.getNome(),pessoa.getSalario(),pessoa.getSexo(),pessoa.getId());    
                   
      int ID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",Integer.class);
      pessoa.setId(ID);
        
    }
   
     public void excluir(int id){
        jdbcTemplate.update("DELETE FROM funcionario where funcionar io_id =?",id);
    
     }
    
}

