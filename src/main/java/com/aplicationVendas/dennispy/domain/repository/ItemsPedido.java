package com.aplicationVendas.dennispy.domain.repository;

import com.aplicationVendas.dennispy.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
