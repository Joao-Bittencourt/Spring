/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.prog3.jezer.exemplo1.controle;

import br.edu.ifrs.restinga.prog3.jezer.exemplo1.modelo.entidade.Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class Teste1 { 
    
    @RequestMapping(path = "/receberPessoa", method = RequestMethod.POST)
    public Pessoa receberPessoa(
            @RequestBody Pessoa pessoa) {
        pessoa.setPeso(Math.round(pessoa.getPeso()*1.2f));
        return pessoa;
                
    }
    
    @RequestMapping(path = "/pessoa")
    public Pessoa retornoPessoa() {
        Pessoa  pessoa = new Pessoa();
        pessoa.setNome("João do Exemplo mudou");
        pessoa.setAltura(1.8f);
        pessoa.setPeso(90);
        pessoa.setEmail("joao@exemplo.com.br");
        pessoa.setTelefones(new String[]{"51 64789654","51 987932963"});
        return pessoa;
    }
    
    
    @RequestMapping(path = "/")
    public String primeiroMetodo() {
    return "Olá, mundo!";
    }
    
    @RequestMapping(path = "/noite")
    public String segundoMetodo() {
        return "Boa noite!"; 
    }
    
    @RequestMapping(path = "/ola/{nome}/{sobrenome}")
    public String ola(@PathVariable String nome, @PathVariable String sobrenome) {
    return "Boa "+sobrenome+", "+nome+" !";
    }
    
    @RequestMapping(path = "/ola2") 
    public String ola2(
            @RequestParam(name = "nome") String nome,
            @RequestParam(name = "sobrenome", required = false ) String sobrenome
            ) {
        if(sobrenome !=null ) {
            return "Olá, "+sobrenome+", "+nome+" !";
        } else {
            return "Olá, "+nome+"!";
        }
    }
    @RequestMapping(path = "/soma/{valor1}")
    public String soma(@PathVariable int valor1, @RequestParam(name = "valor2") int valor2) {
        return "Soma:"+(valor1+ valor2);
    }
    
    
}
