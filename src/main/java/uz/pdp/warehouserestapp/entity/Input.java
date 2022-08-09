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
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @NotNull(message = "WarehouseId should be not null")
    @ManyToOne(optional = false)
    private Warehouse warehouse;

    @NotNull(message = "SupplierId should be not null")
    @ManyToOne(optional = false)
    private Supplier supplier;

    @NotNull(message = "CurrencyId should be not null")
    @ManyToOne(optional = false)
    private Currency currency;

    @NotNull(message = "FactureNumber should be not null")
    @Column(nullable = false)
    private String factureNumber;

    @Column(nullable = false, unique = true)
    private String code;
}
