package uz.pdp.warehouserestapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.warehouserestapp.entity.Output;
import uz.pdp.warehouserestapp.entity.OutputProduct;
import uz.pdp.warehouserestapp.entity.Product;
import uz.pdp.warehouserestapp.payload.OutputProductDTO;
import uz.pdp.warehouserestapp.repository.OutputProductRepository;
import uz.pdp.warehouserestapp.repository.OutputRepository;
import uz.pdp.warehouserestapp.repository.ProductRepository;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class OutputProductService {
    private final OutputProductRepository repository;
    private final ProductRepository productRepository;
    private final OutputRepository outputRepository;

    @Autowired
    public OutputProductService(OutputProductRepository repository, ProductRepository productRepository, OutputRepository outputRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.outputRepository = outputRepository;
    }

    //    GET ALL
    public ResponseEntity<Page<OutputProduct>> getAllOutputProduct(Integer page, Integer size) {
        return new ResponseEntity<>(repository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, size)), OK);
    }

    //    GET ONE
    public ResponseEntity<?> getOneOutputProduct(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = repository.findById(id);
        if (!optionalOutputProduct.isPresent()) return new ResponseEntity<>("Output product not found.", NOT_FOUND);
        return new ResponseEntity<>(optionalOutputProduct.get(), OK);
    }

    //    CREATE
    public ResponseEntity<?> addOutputProduct(OutputProductDTO dto) {
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        if (!optionalProduct.isPresent()) return new ResponseEntity<>("Product not found.", NOT_FOUND);
        Optional<Output> optionalOutput = outputRepository.findById(dto.getOutputId());
        if (!optionalOutput.isPresent()) return new ResponseEntity<>("Output not found.", NOT_FOUND);
        if (dto.getAmount() == null || dto.getAmount() == 0)
            return new ResponseEntity<>("Amount was not entered or the value remained 0", BAD_REQUEST);
        if (dto.getPrice() == null || dto.getPrice() <= 0)
            return new ResponseEntity<>("Price entered incorrectly.", BAD_REQUEST);
        OutputProduct op = new OutputProduct(optionalProduct.get(), dto.getAmount(), dto.getPrice(), optionalOutput.get());
        return new ResponseEntity<>(repository.save(op), CREATED);
    }

    //    UPDATE
    public ResponseEntity<?> editOutputProduct(Integer id, OutputProductDTO dto) {
        Optional<OutputProduct> oop = repository.findById(id);
        if (!oop.isPresent()) return new ResponseEntity<>("Output product not found", NOT_FOUND);
        OutputProduct outputProduct = oop.get();
        Optional<Output> optionalOutput = outputRepository.findById(dto.getOutputId());
        optionalOutput.ifPresent(outputProduct::setOutput);
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        optionalProduct.ifPresent(outputProduct::setProduct);
        if (dto.getPrice() != null && dto.getPrice() > 0) outputProduct.setPrice(dto.getPrice());
        if (dto.getAmount() != null && dto.getAmount() > 0) outputProduct.setAmount(dto.getAmount());
        return new ResponseEntity<>(repository.save(outputProduct),OK);
    }

    //    DELETE
    public ResponseEntity<?> deleteOutput(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = repository.findById(id);
        if (!optionalOutputProduct.isPresent()) return new ResponseEntity<>("Output product not found.", NOT_FOUND);
        try {
            repository.delete(optionalOutputProduct.get());
            return new ResponseEntity<>("Output product successfully deleted.", OK);
        } catch (Exception e) {
            return new ResponseEntity<>("BAD REQUEST", BAD_REQUEST);
        }
    }
}
