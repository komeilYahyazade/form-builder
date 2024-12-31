package com.example.formbuilder.controller;

import com.example.formbuilder.dto.FormDTO;
import com.example.formbuilder.model.Form;
import com.example.formbuilder.service.FormService;
import org.springframework.web.bind.annotation.*;

import com.example.formbuilder.model.Field;
import com.example.formbuilder.dto.FieldDTO;


import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/")
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }

    @PostMapping("/")
    public Form createForm(@RequestBody FormDTO formDTO) {
        return formService.createForm(formDTO);
    }

    @GetMapping("/{id}")
    public Form getFormById(@PathVariable Long id) {
        return formService.getFormById(id);
    }

    @PutMapping("/{id}")
    public Form updateForm(@PathVariable Long id, @RequestBody FormDTO formDTO) {
        return formService.updateForm(id, formDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
    }

    @PostMapping("/{id}/publish")
    public void togglePublishStatus(@PathVariable Long id) {
        formService.togglePublishStatus(id);
    }

    @GetMapping("/published")
    public List<Form> getPublishedForms() {
        return formService.getPublishedForms();
    }

    @GetMapping("/{id}/fields")
    public List<Field> getFieldsByFormId(@PathVariable Long id) {
        return formService.getFieldsByFormId(id);
    }
    @PutMapping("/{id}/fields")
    public Form updateFieldsByFormId(@PathVariable Long id, @RequestBody List<FieldDTO> fieldDTOs) {
        return formService.updateFieldsByFormId(id, fieldDTOs);
    }
}
