package kg.attractor.javasharehub.service.impl;

import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.dto.UploadFileDto;
import kg.attractor.javasharehub.entity.Category;
import kg.attractor.javasharehub.entity.File;
import kg.attractor.javasharehub.entity.User;
import kg.attractor.javasharehub.repository.FileRepository;
import kg.attractor.javasharehub.service.CategoryService;
import kg.attractor.javasharehub.service.FileService;
import kg.attractor.javasharehub.service.UserService;
import kg.attractor.javasharehub.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final FileUtil fileUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, FileUtil fileUtil, @Lazy UserService userService, CategoryService categoryService) {
        this.fileRepository = fileRepository;
        this.fileUtil = fileUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @Override
    public Page<FileDto> getAllPublicFiles(Pageable pageable){
        Page<File> files = fileRepository.findAllByStatus("public", pageable);
        List<FileDto> fileDtoList= files.stream()
                .map(this::convertToFileDto)
                .toList();
        return new PageImpl<>(fileDtoList, pageable, files.getTotalElements());
    }

    @Override
    public Page<FileDto> getAllPublicFilesByCategoryId(Long categoryId, Pageable pageable){
        Page<File> files = fileRepository.findAllByStatusAndCategoryId("public", categoryId, pageable);
        List<FileDto> fileDtoList= files.stream()
                .map(this::convertToFileDto)
                .toList();
        return new PageImpl<>(fileDtoList, pageable, files.getTotalElements());
    }

    @Override
    public void upload(UploadFileDto fileDto, Principal principal) {
        String filename = fileUtil.saveFile(fileDto.getFile(), "files");
        User user = userService.getUserByEmail(principal.getName());
        Category category = categoryService.getCategoryById(fileDto.getCategoryId());
        File file = new File();
        file.setFilename(filename);
        file.setOriginalFilename(fileDto.getFile().getOriginalFilename());
        file.setDownloadCounter(0);
        file.setStatus(fileDto.getStatus());
        file.setUser(user);
        file.setCategory(category);
        fileRepository.save(file);
    }


    @Override
    public ResponseEntity<?> downloadFile(Long fileId){
        File file = fileRepository.findById(fileId).orElseThrow(() -> new NoSuchElementException("File Not Found"));
        String filename = file.getFilename();
        return fileUtil.getOutputFile(filename, "files", MediaType.APPLICATION_OCTET_STREAM);
    }

    @Override
    public FileDto convertToFileDto(File file) {
        return FileDto.builder()
                .id(file.getId())
                .author(file.getUser().getUsername())
                .category(file.getCategory().getName())
                .downloadCounter(file.getDownloadCounter())
                .filename(file.getFilename())
                .originalFilename(file.getOriginalFilename())
                .status(file.getStatus())
                .build();
    }
}
