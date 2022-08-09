package uz.pdp.warehouserestapp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.warehouserestapp.entity.template.AbsEntity;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Currency extends AbsEntity {
}
