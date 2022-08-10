package uz.pdp.warehouserestapp.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.warehouserestapp.entity.Supplier;

@Projection(types = {Supplier.class})
public interface SupplierProjection {
    Integer getId();

    String getName();

    String getPhoneNumber();

    boolean isActive();
}
