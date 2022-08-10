package uz.pdp.warehouserestapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {
    @NotNull
    private String name;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer measurementId;
    @NotNull
    private Integer photoId;
    private boolean active;
}
