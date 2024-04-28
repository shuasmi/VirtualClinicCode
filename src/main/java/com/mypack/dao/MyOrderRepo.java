package com.mypack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypack.entities.MyOrder;

public interface MyOrderRepo extends JpaRepository<MyOrder, Long> {
	
	public MyOrder findByOrderId(String orderId);

}
