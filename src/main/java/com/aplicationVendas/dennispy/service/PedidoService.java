package com.aplicationVendas.dennispy.service;

import com.aplicationVendas.dennispy.domain.entity.Pedido;
import com.aplicationVendas.dennispy.domain.enums.StatusPedido;
import com.aplicationVendas.dennispy.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional <Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
