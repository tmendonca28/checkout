package com.tmendonca.checkout.helper;


import android.content.res.Resources;

import com.tmendonca.checkout.R;
import com.tmendonca.checkout.model.Product;
import com.tmendonca.checkout.model.ShoppingCartEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Product> catalog;
    private static Map<Product, ShoppingCartEntry> cartMap = new HashMap<Product, ShoppingCartEntry>();

    public static List<Product> getCatalog(Resources res){
        if(catalog == null) {
            catalog = new Vector<Product>();
            catalog.add(new Product(" Dishonored", res
                    .getDrawable(R.drawable.dishonored),
                    "Stealth action-adventure video game.", 2000));
            catalog.add(new Product("Assassins Creed", res
                    .getDrawable(R.drawable.ac),
                    "Action-adventure video game developed by Ubisoft.", 1500));
            catalog.add(new Product("Batman: Arkham Knight", res
                    .getDrawable(R.drawable.batman),
                    "Action-adventure video game.", 3000));
            catalog.add(new Product("Crysis 3", res
                    .getDrawable(R.drawable.crysis3),
                    "First-person shooter video game.", 1200));
            catalog.add(new Product("Hitman: Absolution", res
                    .getDrawable(R.drawable.hitman),
                    "Stealth video game developed by IO Interactive.", 1500));
            catalog.add(new Product("Splinter Cell: Blacklist", res
                    .getDrawable(R.drawable.scblacklist),
                    "Action-adventure stealth video game developed by Ubisoft Toronto.", 1200));
        }

        return catalog;
    }

    public static void setQuantity(Product product, int quantity) {
        // Gets current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);

        // If the quantity is zero or less, remove the products
        if(quantity <= 0) {
            if(curEntry != null)
                removeProduct(product);
            return;
        }

        // If a current cart entry doesn't exist, create one
        if(curEntry == null) {
            curEntry = new ShoppingCartEntry(product, quantity);
            cartMap.put(product, curEntry);
            return;
        }

        // Updates quantity
        curEntry.setQuantity(quantity);
    }

    public static int getProductQuantity(Product product) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);

        if(curEntry != null)
            return curEntry.getQuantity();

        return 0;
    }

    public static void removeProduct(Product product) {
        cartMap.remove(product);
    }

    public static List<Product> getCartList() {
        List<Product> cartList = new Vector<Product>(cartMap.keySet().size());
        for(Product p : cartMap.keySet()) {
            cartList.add(p);
        }

        return cartList;
    }


}