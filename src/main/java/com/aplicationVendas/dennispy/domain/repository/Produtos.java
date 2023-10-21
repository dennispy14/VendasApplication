package com.aplicationVendas.dennispy.domain.repository;

import com.aplicationVendas.dennispy.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
}
