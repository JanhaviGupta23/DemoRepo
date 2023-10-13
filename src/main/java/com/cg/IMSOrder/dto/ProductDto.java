package com.cg.IMSOrder.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private int productId;
	private String productName;
	private List<Integer> ingredientId;

}
