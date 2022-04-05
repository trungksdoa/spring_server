package com.example.projectapi.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private int priceHot;
    private int priceCold;
    @ManyToOne(fetch = EAGER)
    private Catagory category = new Catagory();
    private String description;
    private String image;
    ;
    private Long stock;
    private boolean home_display;
    private String expiration_date;
    private String manufacture_date;
    private String Created_date;
    private String Update_date;

}
