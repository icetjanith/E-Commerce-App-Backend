package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.OrderDetails;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @ManyToOne
    @JoinColumn(name = "customerId",referencedColumnName = "userId")
    private UsersEntity usersEntity;
    @OneToMany(mappedBy = "order")
    private List<OrderDetailsEntity> orderDetailsList;

}
