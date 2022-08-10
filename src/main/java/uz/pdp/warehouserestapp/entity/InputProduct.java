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
    private Product product;

    @ManyToOne(optional = false)
    private Input input;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "expire_date", nullable = false)
    private Date expireDate;

    public InputProduct(Product product, Input input, Double amount, Double price, Date expireDate) {
        this.product = product;
        this.input = input;
        this.amount = amount;
        this.price = price;
        this.expireDate = expireDate;
    }
}
