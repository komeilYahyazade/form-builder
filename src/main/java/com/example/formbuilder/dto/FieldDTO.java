package com.example.formbuilder.dto;

import lombok.Data;

@Data
public class FieldDTO {
    private String name;
    private String label;
    private String type; // e.g., Text, Number, Boolean, Date
    private String defaultValue;
}
