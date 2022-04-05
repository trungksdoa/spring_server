package com.example.projectapi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product2 {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("barrel_price")
    private int barrel_price;

    @JsonProperty("whirlwind_price")
    private int whirlwind_price;

    @JsonProperty("Single_price")
    private int Single_price;

    @JsonProperty("Gift")
    private String Gift;

    @JsonProperty("Type")
    private String type;


    @JsonProperty("category")
    @ManyToOne(fetch = EAGER)
    private Catagory category = new Catagory();
}
