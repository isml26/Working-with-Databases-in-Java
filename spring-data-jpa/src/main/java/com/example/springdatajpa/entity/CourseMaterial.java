package com.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "course_material"
)
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    @Column(
            name = "course_material_id",
            nullable = false
    )
    private Long courseMaterialId;
    private String url;

    /*
    LAZY = fetch when needed
    EAGER = fetch immediately

    To load it together with the rest of the fields (i.e. eagerly), or
    To load it on-demand (i.e. lazily) when you call the university's getStudents() method.
     */
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false // whenever save course also course material is required
    )
    @JoinColumn(
            name="course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
