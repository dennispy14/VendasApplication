package com.aplicationTests.dennispy.domain.repository;

import com.aplicationTests.dennispy.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
