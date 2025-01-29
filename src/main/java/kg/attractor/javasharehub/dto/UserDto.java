package kg.attractor.javasharehub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.attractor.javasharehub.validation.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String username;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @UniqueEmail
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 4, max = 20,
            message = "Password length must be >= 4 and <= 20")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Password should contain at least one uppercase letter, one number")
    private String password;
    private List<FileDto> files;
}
