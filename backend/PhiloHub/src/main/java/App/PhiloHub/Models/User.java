package App.PhiloHub.Models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id;

    @NotBlank(message = "email is required")
    @Column(unique = true,name = "email")
    @Email(message = "invalid email type")
    @Size(max = 80,message = "email is too long")
    private String email;

    @NotBlank(message = "username is required")
    @Column(unique = true,name = "username")
    @Size(max = 50,message = "username is too long")
    private String username;

    @Size(max = 50)
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 50)
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 8, max = 70)
    @NotBlank(message = "password is required")
    @Column(name = "password")
    private String password;

    @NotNull(message = "birthday is required")
    @Past(message = "birthdate must be in the past")
    @Column(name = "bithdate")
    private LocalDate bithdate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_country")
    private Country country;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ProfilePicture> profilePictures = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserDiscussion> userDiscussions = new ArrayList<>();

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<DiscussionMessage> discussionMessages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<ReceivedDiscussionMessage> receivedDiscussionMessages = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Feedback> feedbacks = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "last_login")
    private LocalDate lastLogin;

    @Column(name = "is_verified")
    private Boolean isVerified=false;

    @Column(name = "is_banned")
    private Boolean isBanned=false;

    @Column(name = "is_enabled")
    private Boolean enabled=true;

}
