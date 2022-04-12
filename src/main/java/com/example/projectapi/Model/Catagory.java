package com.example.projectapi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catagory)) return false;
        Catagory catagory = (Catagory) o;
        return name.equals(catagory.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
