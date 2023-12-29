package qdo_ln.q_shop.services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import qdo_ln.q_shop.entities.Product;
import qdo_ln.q_shop.repositories.ProductRepository;
import qdo_ln.q_shop.repositories.specifications.ProductSpecification;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@Data
public class ProductService {

    private ProductRepository productRepository;
    private StringBuilder requestDefinition;

    @PostConstruct
    public void initRequestDefinition(){
        requestDefinition = new StringBuilder();
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(int id){
        return productRepository.findById(id).get();
    }

    public Page<Product> findAll(Map<String,String> param){
        return productRepository.findAll(buildSpecification(param), buildPageRequest(param));
    }

    private Specification<Product> buildSpecification(Map<String, String> param){
        Specification<Product> specification = Specification.where(null);
        if(param.containsKey("min_price") && !param.get("min_price").isEmpty()){
            double minPrice = Double.parseDouble(param.get("min_price"));
            specification = specification.and(ProductSpecification.priceGE(minPrice));
            requestDefinition.append("&min_price=").append(minPrice);
        }
        if(param.containsKey("max_price") && !param.get("max_price").isEmpty()){
            double maxPrice = Double.parseDouble(param.get("max_price"));
            specification = specification.and(ProductSpecification.priceLE(maxPrice));
            requestDefinition.append("&max_price=").append(maxPrice);
        }
        if(param.containsKey("category") && !param.get("category").isEmpty()){
            int categoryId = Integer.parseInt(param.get("category"));
            specification = specification.and(ProductSpecification.categoryEq(categoryId));
            requestDefinition.append("&category=").append(categoryId);
        }
        return specification;
    }

    private Pageable buildPageRequest(Map<String, String> param){
        int pageIndex=0;
        int pageSize=10;

        if(param.containsKey("p") && !param.get("p").isEmpty()){
            pageIndex = Integer.parseInt(param.get("p"));
        }
        if(param.containsKey("page_size") && !param.get("page_size").isEmpty()){
            pageSize = Integer.parseInt(param.get("page_size"));
            requestDefinition.append("&page_size=").append(pageSize);
        }

        String sortType = "id";
        Sort.Direction sortDirection = Sort.Direction.ASC;

        if(param.containsKey("sort_type") && !param.get("sort_type").isEmpty()){
            sortType=param.get("sort_type");
            requestDefinition.append("&sort_type=").append(sortType);
        }

        if(param.containsKey("sort_direction") && !param.get("sort_direction").isEmpty()){
            String sortDir = param.get("sort_direction");
            sortDirection = Sort.Direction.valueOf(sortDir);
            requestDefinition.append("&sort_direction=").append(sortDir);
        }
        return PageRequest.of(pageIndex, pageSize, Sort.by(sortDirection, sortType));
    }
}
