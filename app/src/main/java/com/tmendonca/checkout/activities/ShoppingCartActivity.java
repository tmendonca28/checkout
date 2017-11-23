package com.tmendonca.checkout.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.tmendonca.checkout.ProductAdapter;
import com.tmendonca.checkout.R;
import com.tmendonca.checkout.helper.ShoppingCartHelper;
import com.tmendonca.checkout.model.Product;

import java.util.List;

public class ShoppingCartActivity extends Activity {

    private List<Product> mCartList;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);


        mCartList = ShoppingCartHelper.getCartList();

        // Clear any selections
        for(int i=0; i<mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }

        // Creates list
        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mProductAdapter);

        listViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(),ProductDetailsActivity.class);
                productDetailsIntent.putExtra(ShoppingCartHelper.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

    }

    public void goToCheckout(View view) {
        Intent intent = new Intent(ShoppingCartActivity.this, PaymentActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the data
        if(mProductAdapter != null) {
            mProductAdapter.notifyDataSetChanged();
        }
    }

}
