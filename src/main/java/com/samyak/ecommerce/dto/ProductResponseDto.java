package com.samyak.ecommerce.dto;

public class ProductResponseDto {

    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private String description;

    public ProductResponseDto() {
    }

    public ProductResponseDto(
            Long productId,
            String productName,
            Double price,
            Integer quantity,
            String description) {

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }
}