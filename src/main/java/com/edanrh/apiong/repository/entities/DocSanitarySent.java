package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sanitary_sent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocSanitarySent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Document of sanitary sent can't be empty")
    @Column(name = "name", nullable = false)
    private Long documentSanitary;

    public DocSanitarySent(Long documentSanitary){
        this.documentSanitary = documentSanitary;
    }
}
