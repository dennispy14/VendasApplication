package com.aplicationVendas.dennispy.execeptions;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException() {
        super("Senha inválida");
    }
}
