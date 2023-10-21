package com.aplicationTests.dennispy.service;

import com.aplicationTests.dennispy.domain.entity.Pedido;
import com.aplicationTests.dennispy.domain.enums.StatusPedido;
import com.aplicationTests.dennispy.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional <Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
