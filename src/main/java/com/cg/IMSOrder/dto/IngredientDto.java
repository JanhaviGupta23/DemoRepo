package com.cg.IMSOrder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {
	private int ingredientId;
	private String ingredientName;
	private String ingredientQuantity;

}
