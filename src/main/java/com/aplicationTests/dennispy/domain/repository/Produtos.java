package com.aplicationTests.dennispy.domain.repository;

import com.aplicationTests.dennispy.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
}
