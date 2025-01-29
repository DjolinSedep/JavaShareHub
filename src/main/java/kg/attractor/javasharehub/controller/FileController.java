package kg.attractor.javasharehub.controller;

import jakarta.validation.Valid;
import kg.attractor.javasharehub.dto.CategoryDto;
import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.dto.UploadFileDto;
import kg.attractor.javasharehub.service.CategoryService;
import kg.attractor.javasharehub.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final CategoryService categoryService;

    @GetMapping
    public String getAllFiles(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<FileDto> files = fileService.getAllPublicFiles(pageable);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("files", files);
        return "file/files";
    }

    @GetMapping("{categoryId}")
    public String getAllFilesByCategory(@PathVariable Long categoryId, Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<FileDto> files = fileService.getAllPublicFilesByCategoryId(categoryId, pageable);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("files", files);
        return "file/files";
    }

    @GetMapping("download/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable Long fileId) {
        return fileService.downloadFile(fileId);
    }

    @GetMapping("files/upload")
    public String upload(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "file/upload";
    }

    @PostMapping("files/upload")
    public String uploadFile(@Valid UploadFileDto uploadFileDto, Model model, Principal principal) {
        fileService.upload(uploadFileDto, principal);
        model.addAttribute("message", "Файл успешно загружен");
        return "files/uploadSuccess";
    }

}
