package uz.pdp.warehouserestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.warehouserestapp.entity.Client;

import javax.validation.Valid;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
@RepositoryRestResource(path = "client", collectionResourceRel = "data")
public interface ClientRepository extends JpaRepository<Client, Integer> {
      @Override
    <S extends Client> S save(@Valid S entity);
}
