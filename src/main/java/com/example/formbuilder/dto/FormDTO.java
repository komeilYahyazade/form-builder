package com.example.formbuilder.dto;

import lombok.Data;

import java.util.List;

@Data
public class FormDTO {
    private String name;
    private List<FieldDTO> fields;
    private String submissionEndpoint;
}
