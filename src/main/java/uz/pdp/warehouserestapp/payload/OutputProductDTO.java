package uz.pdp.warehouserestapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OutputProductDTO {
    @NotNull
    private Integer productId;
    @NotNull
    private Double amount;
    @NotNull
    private Double price;
    @NotNull
    private Integer outputId;
}
