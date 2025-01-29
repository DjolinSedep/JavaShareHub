package kg.attractor.javasharehub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String filename;
    private String originalFilename;
    private int downloadCounter;
    private String status;
    private String privateKey;
    private String author;
    private String category;
}
