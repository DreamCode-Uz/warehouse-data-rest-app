package uz.pdp.warehouserestapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.warehouserestapp.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Product extends AbsEntity {

    private String code;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Measurement measurement;
}
