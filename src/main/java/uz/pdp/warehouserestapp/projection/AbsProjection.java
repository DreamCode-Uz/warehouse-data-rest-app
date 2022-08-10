package uz.pdp.warehouserestapp.projection;


import org.springframework.data.rest.core.config.Projection;
import uz.pdp.warehouserestapp.entity.Currency;
import uz.pdp.warehouserestapp.entity.Measurement;
import uz.pdp.warehouserestapp.entity.Warehouse;

@Projection(types = {Currency.class, Warehouse.class, Measurement.class})
public interface AbsProjection {

    Integer getId();

    String getName();

    boolean isActive();
}
