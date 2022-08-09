package uz.pdp.warehouserestapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class InputProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Input input;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "price")
    private Double price;

    @Column(name = "expire_date")
    private Date expireDate;
}
