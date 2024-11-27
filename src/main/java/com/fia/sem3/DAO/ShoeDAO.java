package com.fia.sem3.DAO;
import com.fia.sem3.Entity.ShoeEntity;
import java.util.ArrayList;
import java.util.List;
public class ShoeDAO {
	 private static List<ShoeEntity> shoes = new ArrayList<>();

	    static {
	        
	        shoes.add(new ShoeEntity(1, "Nike", "Air Max", 150.0));
	        shoes.add(new ShoeEntity(2, "Adidas", "Superstar", 120.0));
	        shoes.add(new ShoeEntity(3, "Puma", "RS-X", 100.0));
	       
	    }

	    public List<ShoeEntity> getAllShoes() {
	        return shoes;
	    }

	    public List<ShoeEntity> getShoesByBrand(String brand) {
	        List<ShoeEntity> filteredShoes = new ArrayList<>();
	        for (ShoeEntity shoe : shoes) {
	            if (shoe.getMarca().equalsIgnoreCase(brand)) {
	                filteredShoes.add(shoe);
	            }
	        }
	        return filteredShoes;
	    }
}
