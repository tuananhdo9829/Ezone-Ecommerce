package com.tuananhdo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderDTO {
    List<OrderItemDTO> itemDTOS;
}
