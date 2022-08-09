package uz.pdp.warehouserestapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Product product;

    @ManyToOne(optional = false)
    private Input input;

    @NotNull
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "expire_date")
    private Date expireDate;
}
