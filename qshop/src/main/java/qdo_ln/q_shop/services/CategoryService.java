package qdo_ln.q_shop.services;

import org.springframework.stereotype.Service;
import qdo_ln.q_shop.entities.Category;
import qdo_ln.q_shop.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
