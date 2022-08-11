package uz.pdp.warehouserestapp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDTO {
    @NotBlank
    private String name;
    private boolean active;
    private Integer categoryId;
}
