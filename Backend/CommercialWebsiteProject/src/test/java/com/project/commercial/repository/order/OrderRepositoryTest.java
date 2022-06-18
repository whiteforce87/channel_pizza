package com.project.commercial.repository.order;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.project.commercial.controller.SequenceGeneratorService;
import com.project.commercial.model.Basket;
import com.project.commercial.model.Order;
import com.project.commercial.repository.OrderRepository;
import com.project.commercial.service.OrderService;

class OrderRepositoryTest {
	
	@InjectMocks
	private OrderService orderService;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private SequenceGeneratorService service;
	
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	

	@Test
	void testFindProductById() {
		List<Basket> products = new ArrayList<>();
		Basket product = new Basket(1, "Roma", "Mozarella, peper, tomato", "S", 3.0, 1);
		products.add(product);
		Order order = new Order(1, 5.0, products);
		
		Mockito.when(orderRepository.findProductById(anyLong())).thenReturn(order);
		
		assertEquals(1, order.getId());
	}

	@Test
	void testSaveOrder() {
		List<Basket> products = new ArrayList<>();
		Basket product = new Basket(1, "Roma", "Mozarella, peper, tomato", "S", 3.0, 1);
		products.add(product);
		Order order = new Order(4.5, products);

		
		Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);
		Mockito.when(service.generateSequence(anyString())).thenReturn(1);

		order.setId(service.generateSequence(Order.SEQUENCE_NAME));

		assertNotNull(order.getId());
		assertEquals(1, order.getId());
		assertEquals(products, order.getBasket());
	}



}