package com.nickolasfisher.webflux.Reactive.client;

import com.nickolasfisher.webflux.Reactive.dto.Product;
import com.nickolasfisher.webflux.Reactive.dto.Promotion;
import com.nickolasfisher.webflux.Reactive.dto.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class ProductAggregate {

    private Product product;
    private Promotion promotion;
    private List<Review> reviews;
}
