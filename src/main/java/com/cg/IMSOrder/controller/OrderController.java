package com.cg.IMSOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.IMSOrder.exception.IdNotFoundException;
import com.cg.IMSOrder.exception.IngredientsNotAvailable;
import com.cg.IMSOrder.pojos.Orders;
import com.cg.IMSOrder.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orders> postIngredient(@RequestBody Orders orders) throws IngredientsNotAvailable, IdNotFoundException{
		return new ResponseEntity<Orders>(orderService.addOrder(orders),HttpStatus.OK);
	}
	
	@PutMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orders> putIngredient(@RequestBody Orders orders) throws IdNotFoundException{
		return new ResponseEntity<Orders>(orderService.updateOrder(orders),HttpStatus.OK);
	}

	@GetMapping(value="/order/{order}")
	public ResponseEntity<Orders> getIngredient(@PathVariable int order) throws IdNotFoundException{
		return new ResponseEntity<Orders>(orderService.getOrder(order), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/order/{order}")
	public ResponseEntity<Orders> deleteIngredient(@PathVariable int order) throws IdNotFoundException{
		return new ResponseEntity<Orders>(orderService.deleteOrder(order), HttpStatus.OK);
	}

}
