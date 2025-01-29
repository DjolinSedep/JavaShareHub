package kg.attractor.javasharehub.service;

import kg.attractor.javasharehub.dto.CategoryDto;
import kg.attractor.javasharehub.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long categoryId);

    List<CategoryDto> getAllCategories();

    CategoryDto convertToCategoryDto(Category category);
}
