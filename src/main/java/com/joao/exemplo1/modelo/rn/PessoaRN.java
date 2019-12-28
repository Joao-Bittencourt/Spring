/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joao.exemplo1.modelo.rn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joao.exemplo1.excecoes.QuebraRN;
import com.joao.exemplo1.modelo.dao.PessoaDAO;
import com.joao.exemplo1.modelo.entidade.Pessoa;

@Component
public class PessoaRN {
    @Autowired
    PessoaDAO pessoaDAO;
    
    private void validar(Pessoa pessoa) {
        if(pessoa.getNome()==null||pessoa.getNome().trim().isEmpty())
            throw new QuebraRN("Nome obrigatorio!");
        if(pessoa.getEmail()==null||pessoa.getEmail().trim().isEmpty())
            throw new QuebraRN("E-mail obrigatorio!");
        if(!pessoa.getEmail().contains("@"))
            throw new QuebraRN("E-mail deve ter '@'!");
        
    }
    public void validarCadastro(Pessoa pessoa) {
        validar(pessoa);
        /// adicionar regras de negócio para cadastro
    }
    public void validarAtualizacao(Pessoa pessoaNova) {
        validar(pessoaNova);
        Pessoa pessoaBanco = pessoaDAO.recuperar(pessoaNova.getId());
        /// adicionar regras de negócio para atualização: 
        if(!pessoaNova.getEmail().equals(pessoaBanco.getEmail()))
            throw new QuebraRN("E-mail não pode ser alterado!");
        
        
    }
           
}
