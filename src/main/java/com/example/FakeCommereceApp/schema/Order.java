package com.example.FakeCommereceApp.schema;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")

public class Order extends BaseEntity {
    private OrderStatus status;
     
    @ManyToMany 
    @JoinTable(name = "order_products" ,
    joinColumns = @JoinColumn(name = "order_id"),  // the fk belogs to the order table -- Order table 
    inverseJoinColumns = @JoinColumn(name = "product_id")) // the fk belongs to the product table -- Product table
    private List<Product> products;
}
