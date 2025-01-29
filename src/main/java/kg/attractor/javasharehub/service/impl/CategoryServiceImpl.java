package kg.attractor.javasharehub.service.impl;

import kg.attractor.javasharehub.dto.CategoryDto;
import kg.attractor.javasharehub.entity.Category;
import kg.attractor.javasharehub.repository.CategoryRepository;
import kg.attractor.javasharehub.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    @Override
    public List<CategoryDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToCategoryDto)
                .toList();
    }


    @Override
    public CategoryDto convertToCategoryDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
