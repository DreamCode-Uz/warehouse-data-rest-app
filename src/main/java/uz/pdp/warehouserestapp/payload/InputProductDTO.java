package uz.pdp.warehouserestapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class InputProductDTO {
    @NotNull
    private Integer productId;
    @NotNull
    private Integer inputId;
    @NotNull
    private Double amount;
    @NotNull
    private Double price;
    @NotNull
    private Date expireDate;
}
