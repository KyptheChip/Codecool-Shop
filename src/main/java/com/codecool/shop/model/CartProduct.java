package com.codecool.shop.model;

import java.math.BigDecimal;

public class CartProduct extends Product{
    private int quantity;

    public CartProduct(Product product) {
        super(product.getId(), product.getName(), product.getDefaultPrice(), product.getDefaultCurrency().toString(), product.getDescription(), product.getProductCategory(), product.getSupplier());
        quantity = 1;
    }

    @Override
    public String getPrice() {
        return this.getDefaultPrice().multiply(BigDecimal.valueOf(quantity)) + " " + this.getDefaultCurrency().toString();
    }

    public void add() {
        quantity++;
    }

    public void remove() {
        quantity--;
    }

    public int getQuantity() {
        return quantity;
    }
}
