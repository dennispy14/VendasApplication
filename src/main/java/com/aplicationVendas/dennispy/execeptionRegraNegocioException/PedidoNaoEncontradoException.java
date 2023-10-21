package com.aplicationVendas.dennispy.execeptionRegraNegocioException;

public class PedidoNaoEncontradoException  extends  RuntimeException{

    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado.");
    }
}
