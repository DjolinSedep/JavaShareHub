package kg.attractor.javasharehub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<File> files;
}
