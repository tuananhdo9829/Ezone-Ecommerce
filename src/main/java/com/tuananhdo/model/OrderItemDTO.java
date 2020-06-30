package com.tuananhdo.model;

import com.tuananhdo.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemDTO {
    private long number;
    private Product productDTO;
}
