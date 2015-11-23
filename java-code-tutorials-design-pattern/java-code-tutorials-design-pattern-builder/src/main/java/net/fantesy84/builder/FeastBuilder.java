/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.builder
 * 文件名: FeastBuilder.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder;

import java.util.ArrayList;
import java.util.List;

import net.fantesy84.builder.domain.Drink;
import net.fantesy84.builder.domain.Food;
import net.fantesy84.builder.factory.IDrinkFactory;
import net.fantesy84.builder.factory.IFoodAndDrinkFactory;
import net.fantesy84.builder.factory.IFoodFactory;
import net.fantesy84.builder.factory.impl.FoodAndDrinkFactoryImpl;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class FeastBuilder {
	private String[] codes;
	private IFoodAndDrinkFactory fdFactory;
	
	/**
	 * 
	 */
	public FeastBuilder() {
		super();
		fdFactory = new FoodAndDrinkFactoryImpl();
	}

	public void createFeast(){
		IFoodFactory foodFactory = fdFactory.makeFood();
		IDrinkFactory drinkFactory = fdFactory.makeDrink();
		List<Food> flist = new ArrayList<Food>(0);
		for (int i = 0; i < codes.length; i++) {
			Food food = foodFactory.cooked(codes[i]);
			if (food != null) {
				flist.add(food);
			}
		}
		List<Drink> dlist = new ArrayList<Drink>(0);
		for (int i = 0; i < codes.length; i++) {
			Drink drink = drinkFactory.take(codes[i]);
			if (drink != null) {
				dlist.add(drink);
			}
		}
		for (Food food : flist) {
			System.out.println(food.getName());
		}
		for (Drink drink : dlist) {
			System.out.println(drink.getName());
		}
	}

	/**
	 * @return the codes
	 */
	public String[] getCodes() {
		return codes;
	}

	/**
	 * @param codes the codes to set
	 */
	public void setCodes(String[] codes) {
		this.codes = codes;
	}
	
}
