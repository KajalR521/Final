package com.onlinepizza.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlinepizza.dto.PizzaDTO;
import com.onlinepizza.entity.Pizza;
import com.onlinepizza.exception.InvalidMinCostException;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer>{

	@Query("Select p from Pizza p where p.pizzaCost between :minCost and :maxCost")
	public List<Pizza> viewPizzaList(@Param("minCost")double minCost, @Param("maxCost")double maxCost);
	
	@Query("Select p from Pizza p where p.pizzaId in :ids")
	public List<Pizza> findPizza(@Param("ids")List<Integer> ids);
	
	@Query("Select sum(p) from Pizza p where p.pizzaId in :ids")
	public double pizzaTotalCost(@Param("ids")List<Integer> ids);
}
