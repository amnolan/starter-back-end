package com.shoppy.guy.myshop.mishop.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("price")
    private String price;
    @JsonProperty("itemDescription")
    private String itemDescription;

}
