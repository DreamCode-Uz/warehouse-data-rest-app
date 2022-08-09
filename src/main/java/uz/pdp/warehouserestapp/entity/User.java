package uz.pdp.warehouserestapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Pattern(regexp = "^\\\\d{10}$")
    @Column(nullable = false)
    private String phoneNumber;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    @NotNull
    @Column(nullable = false)
    private String password;

    private boolean active = true;

    @ManyToMany
    private List<Warehouse> warehouses;
}
