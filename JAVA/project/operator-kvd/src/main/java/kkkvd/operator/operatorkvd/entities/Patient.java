package kkkvd.operator.operatorkvd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "middle_name", length = 100)
    private String middleName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "address", length = 255)
    private String address;

    @ManyToOne
    @JoinColumn(name = "gender_id",  nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
}
