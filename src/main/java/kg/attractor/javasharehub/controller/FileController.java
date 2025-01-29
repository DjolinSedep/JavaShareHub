package kg.attractor.javasharehub.controller;

import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping
    public String getAllFiles(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<FileDto> files = fileService.getAllPublicFiles(pageable);
        model.addAttribute("files", files);
        return "file/files";
    }
}
