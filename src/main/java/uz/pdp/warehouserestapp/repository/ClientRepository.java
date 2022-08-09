package uz.pdp.warehouserestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.warehouserestapp.entity.Client;

@RepositoryRestResource(path = "client", collectionResourceRel = "data")
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
