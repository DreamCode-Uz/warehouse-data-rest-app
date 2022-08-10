package uz.pdp.warehouserestapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class InputDTO {

    @NotNull(message = "Date should be not null")
    private Date date;
    @NotNull(message = "WarehouseId should be not null")
    private Integer warehouseId;
    @NotNull(message = "SupplierId should be not null")
    private Integer supplierId;
    @NotNull(message = "CurrencyId should be not null")
    private Integer currencyId;
    @NotNull(message = "Facture number should be not null")
    private String factureNumber;
}
