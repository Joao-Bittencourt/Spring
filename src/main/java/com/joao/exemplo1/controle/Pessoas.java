/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joao.exemplo1.controle;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.joao.exemplo1.excecoes.NaoEncontrado;
import com.joao.exemplo1.excecoes.QuebraRN;
import com.joao.exemplo1.modelo.dao.PessoaDAO;
import com.joao.exemplo1.modelo.entidade.Pessoa;
import com.joao.exemplo1.modelo.rn.PessoaRN;

/**
 *
 * @author jezer
 */
@Controller
@ResponseBody
public class Pessoas {

    @Autowired
    PessoaDAO pessoaDAO;
    
    @Autowired
    PessoaRN pessoaRN;

    @RequestMapping(path = "/listarPessoas", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Pessoa> listarPessoas() {
        return pessoaDAO.listar();
    }

    @RequestMapping(path = "/recuperarPessoa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> recuperarPessoa(@PathVariable int id) {
        // Exemplo usando ResponseEntity 
        Pessoa pessoa = pessoaDAO.recuperar(id);
        if (pessoa == null) {
            ResponseEntity resposta = ResponseEntity
                    .notFound()
                    .header("treco", "não fica no corpo")
                    .build();
            
            return resposta;
        } else {
            ResponseEntity resposta = new ResponseEntity<>(pessoa, HttpStatus.OK);
            return resposta;
        }
    }

    @RequestMapping(path = "/inserirPessoa", method = RequestMethod.POST)
    public Pessoa inserirPessoa(@RequestBody Pessoa pessoa) {
        
        pessoaRN.validarCadastro(pessoa);
            
        return pessoaDAO.inserir(pessoa);
    }

    @RequestMapping(path = "/excluirPessoa/{id}", method = RequestMethod.GET)
    public void excluirPessoa(@PathVariable int id) {
        if(pessoaDAO.recuperar(id)==null)
            throw new NaoEncontrado("ID:"+id+" não encontrada!");
        
        pessoaDAO.excluir(id);
    }

    @RequestMapping(path = "/atualizarPessoa", method = RequestMethod.POST)
    public void atualizarPessoa(@RequestBody Pessoa pessoaNova) {
        pessoaRN.validarAtualizacao(pessoaNova);
        pessoaDAO.atualizar(pessoaNova);
    }
    @RequestMapping(path="/testebanco/{a}", method = RequestMethod.GET)
    public void listarTeste(@PathVariable int a){
        pessoaDAO.listar(a);
        
    }
}
