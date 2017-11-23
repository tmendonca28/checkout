package com.tmendonca.checkout.activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tmendonca.checkout.R;
import com.tmendonca.checkout.helper.ShoppingCartHelper;
import com.tmendonca.checkout.model.Product;

import java.util.List;

public class ProductDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());

        int productIndex = getIntent().getExtras().getInt(ShoppingCartHelper.PRODUCT_INDEX);
        final Product selectedProduct = catalog.get(productIndex);

        ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);
        productImageView.setImageDrawable(selectedProduct.productImage);
        TextView productTitleTextView = (TextView) findViewById(R.id.TextViewProductTitle);
        productTitleTextView.setText(selectedProduct.title);
        TextView productDetailsTextView = (TextView) findViewById(R.id.TextViewProductDetails);
        productDetailsTextView.setText(selectedProduct.description);

        // Updates current quantity in the cart
        TextView textViewCurrentQuantity = (TextView) findViewById(R.id.textViewCurrentlyInCart);
        textViewCurrentQuantity.setText("Currently in Cart: " + ShoppingCartHelper.getProductQuantity(selectedProduct));

        // Saves reference to quantity edit text
        final EditText editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);

        Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);
        addToCartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Validations
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(editTextQuantity.getText()
                            .toString());

                    if (quantity < 0) {
                        Toast.makeText(getBaseContext(),
                                "Please enter a quantity of 0 or higher",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(),
                            "Please enter a numeric quantity",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                // Valid quantity entered
                ShoppingCartHelper.setQuantity(selectedProduct, quantity);

                // Closes activity
                finish();
            }
        });

    }

}

