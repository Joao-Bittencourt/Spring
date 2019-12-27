/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.prog3.jezer.exemplo1.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jezer
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuebraRN extends RuntimeException {
    public QuebraRN(String msg) {
        super(msg);
    }
    
}
