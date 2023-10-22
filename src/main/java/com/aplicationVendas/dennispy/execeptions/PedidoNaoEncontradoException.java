package com.aplicationVendas.dennispy.execeptions;

public class PedidoNaoEncontradoException  extends  RuntimeException{

    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado.");
    }
}
