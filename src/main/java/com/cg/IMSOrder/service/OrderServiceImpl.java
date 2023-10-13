package com.cg.IMSOrder.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cg.IMSOrder.dto.CategoryDto;
import com.cg.IMSOrder.dto.IngredientDto;
import com.cg.IMSOrder.dto.ProductDto;
import com.cg.IMSOrder.exception.IdNotFoundException;
import com.cg.IMSOrder.exception.IngredientsNotAvailable;
import com.cg.IMSOrder.pojos.Orders;
import com.cg.IMSOrder.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	RestTemplate restTemplate;


	public Orders addOrder(Orders orders) throws IdNotFoundException, IngredientsNotAvailable {

		String urlCategory="http://localhost:2002/categories/"+orders.getCategoryId();
		ResponseEntity<CategoryDto> responseCategory=restTemplate.getForEntity(urlCategory, CategoryDto.class);
		CategoryDto cdto=responseCategory.getBody();

		int f=0;

		try {
			ProductDto pdto=restTemplate.getForEntity("http://localhost:2004/product/"+orders.getProductId(), ProductDto.class).getBody();
			List<Integer> ingredientList=pdto.getIngredientId();
			for(Integer i:ingredientList){
				IngredientDto idto=restTemplate.getForEntity("http://localhost:2003/ingredient/"+i, IngredientDto.class).getBody();
				int quantity  = Integer.parseInt(idto.getIngredientQuantity());
				if(quantity==0) {
					f=1;
					break;
				}
				else {
					idto.setIngredientQuantity(Integer.toString(quantity-1));
					restTemplate.put("http://localhost:2003/ingredient",idto);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			throw new IdNotFoundException("Product Not found");
		}
	
	if(f==1) {
		throw new IngredientsNotAvailable("Ingredient Not Available");
	}
	else {
		return orderRepository.save(orders);
	}
}
public Orders updateOrder(Orders orders) throws IdNotFoundException {
	Orders obj=getOrder(orders.getOrderId());
	obj.setCategoryId(orders.getCategoryId());
	obj.setProductId(orders.getProductId());
	obj.setCustomerName(orders.getCustomerName());
	return orderRepository.save(obj);
}
public Orders getOrder(int orderId) throws IdNotFoundException {

	return orderRepository.findById(orderId).orElseThrow(() -> new IdNotFoundException("Order Not Found"));
}
public Orders deleteOrder(int orderId) throws IdNotFoundException{
	Orders orders=orderRepository.findById(orderId).orElseThrow(() -> new IdNotFoundException("Order Not Found"));
	orderRepository.delete(orders);
	return orders;
}

}














//List<Integer> ingredientList=pdto.getIngredients();
//for(Integer i:ingredientList){
//IngredientDto idto=restTemplate.getForEntity("http://localhost:2003/ingredient/"+i, IngredientDto.class).getBody();
//int quantity  = Integer.parseInt(idto.getIngredientQuantity());
//if(quantity==0) {
//	f=1;
//	break;
//}
//else {
//	idto.setIngredientQuantity(Integer.toString(quantity-1));
//	restTemplate.put("http://localhost:2003/ingredient",idto);
//}
//
//}
//List<IngredientDto> ingredientList = Arrays.stream(ingredientArray).toList();
//for(IngredientDto ingredientdto:ingredientList) {
//	if(Integer.parseInt(ingredientdto.getIngredientQuantity())==0) {
//		f=1;
//		break;
//	}
//	else {
//		IngredientDto idto=restTemplate.getForEntity("http://localhost:2003/ingredient/"+ingredientdto.getIngredientId(), IngredientDto.class).getBody();
//		idto.setIngredientQuantity(Integer.toString(Integer.parseInt(ingredientdto.getIngredientQuantity())-1));
//		restTemplate.put("http://localhost:2003/ingredient",idto);
//	}
//}	
