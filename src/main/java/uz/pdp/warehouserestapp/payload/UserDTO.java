package uz.pdp.warehouserestapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UserDTO {
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;
    private boolean active;
    @NotNull
    private Set<Integer> warehouseId;
}
