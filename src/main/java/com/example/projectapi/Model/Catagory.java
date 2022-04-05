package com.example.projectapi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catagory {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @JsonProperty("name")
    private String name;
    private Long parent;
}
