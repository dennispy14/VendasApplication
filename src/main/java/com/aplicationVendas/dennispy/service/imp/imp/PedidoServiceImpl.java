package com.aplicationVendas.dennispy.service.imp.imp;


import com.aplicationVendas.dennispy.domain.entity.Cliente;
import com.aplicationVendas.dennispy.domain.entity.ItemPedido;
import com.aplicationVendas.dennispy.domain.entity.Pedido;
import com.aplicationVendas.dennispy.domain.entity.Produto;
import com.aplicationVendas.dennispy.domain.enums.StatusPedido;
import com.aplicationVendas.dennispy.domain.repository.Clientes;
import com.aplicationVendas.dennispy.domain.repository.ItemsPedido;
import com.aplicationVendas.dennispy.domain.repository.Pedidos;
import com.aplicationVendas.dennispy.domain.repository.Produtos;
import com.aplicationVendas.dennispy.execeptions.PedidoNaoEncontradoException;
import com.aplicationVendas.dennispy.execeptions.RegraNegocioException;
import com.aplicationVendas.dennispy.rest.dto.ItemPedidoDTO;
import com.aplicationVendas.dennispy.rest.dto.PedidoDTO;
import com.aplicationVendas.dennispy.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clienteRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;

    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }


    @Override
    @Transactional
    public void atualizaStatus( Integer id, StatusPedido statusPedido ) {
        repository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException() );
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}