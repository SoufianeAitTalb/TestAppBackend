package com.indatacore.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CSVDTO {
    private String name;
    private Long quantity;
    private Double price;
    private String category;
}
