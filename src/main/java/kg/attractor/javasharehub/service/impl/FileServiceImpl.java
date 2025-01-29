package kg.attractor.javasharehub.service.impl;

import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.entity.File;
import kg.attractor.javasharehub.repository.FileRepository;
import kg.attractor.javasharehub.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;


    @Override
    public Page<FileDto> getAllPublicFiles(Pageable pageable){
        Page<File> files = fileRepository.findAllByStatus("public", pageable);
        List<FileDto> fileDtoList= files.stream()
                .map(this::convertToFileDto)
                .toList();
        return new PageImpl<>(fileDtoList, pageable, files.getTotalElements());
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
