package com.example.formbuilder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String label;
    private String type; // e.g., Text, Number, Boolean, Date
    private String defaultValue;

    @ManyToOne
    @JoinColumn(name = "form_id")
    @JsonBackReference
    private Form form;
}
