package kg.attractor.javasharehub.controller;

import jakarta.validation.Valid;
import kg.attractor.javasharehub.dto.CategoryDto;
import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.dto.UploadFileDto;
import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.entity.File;
import kg.attractor.javasharehub.service.CategoryService;
import kg.attractor.javasharehub.service.FileService;
import kg.attractor.javasharehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final UserService userService;
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
        File file = fileService.getFileById(fileId);
        if (!"public".equalsIgnoreCase(file.getStatus())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This file is not available for public download");
        }
        return fileService.downloadFile(fileId);
    }

    @GetMapping("download/{privateKey}/{fileId}")
    public ResponseEntity<?> downloadFileByPrivateKey(@PathVariable String privateKey, @PathVariable Long fileId) {
        File file = fileService.getFileById(fileId);
        if (file == null || !"private".equalsIgnoreCase(file.getStatus())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid or inaccessible private file");
        }
        return fileService.downloadByPrivateKey(privateKey, fileId);
    }

    @GetMapping("files/upload")
    public String upload(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "file/upload";
    }

    @PostMapping("files/upload")
    public String uploadFile(@Valid UploadFileDto uploadFileDto, Principal principal, Model model) {
        fileService.upload(uploadFileDto, principal);
        UserDto user = userService.getUserDtoByEmail(principal.getName());
        model.addAttribute("user", user);
        return "profile/profile";
    }

    @PostMapping("files/{fileId}/generate-private-key")
    public String generatePrivateKey(@PathVariable Long fileId, Model model) {
        String privateKey = fileService.generatePrivateKey(fileId);
        model.addAttribute("privateKey", privateKey);
        model.addAttribute("fileId", fileId);
        return "file/private-key";
    }

}
