package com.dalytekam.inventory.Dao;

import com.dalytekam.inventory.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {
@Query("select p from Product p where p.name like :x")
    Page<Product> search(@Param("x") String kw, Pageable pageable);
}
