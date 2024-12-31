package com.example.formbuilder.service;

import com.example.formbuilder.dto.FormDTO;
import com.example.formbuilder.dto.FieldDTO;
import com.example.formbuilder.model.Field;
import com.example.formbuilder.model.Form;
import com.example.formbuilder.repository.FieldRepository;
import com.example.formbuilder.repository.FormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final FieldRepository fieldRepository;

    public FormServiceImpl(FormRepository formRepository, FieldRepository fieldRepository) {
        this.formRepository = formRepository;
        this.fieldRepository = fieldRepository;
    }

    @Override
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @Override
    public Form getFormById(Long id) {
        return formRepository.findById(id).orElseThrow(() -> new RuntimeException("Form not found"));
    }

    @Override
    public Form createForm(FormDTO formDTO) {
        Form form = new Form();
        form.setName(formDTO.getName());
        form.setSubmissionEndpoint(formDTO.getSubmissionEndpoint());
        form.setPublished(false);

        // Initialize the fields list if it's null
        if (form.getFields() == null) {
            form.setFields(new ArrayList<>());
        }

        formDTO.getFields().forEach(fieldDTO -> {
            Field field = new Field();
            field.setName(fieldDTO.getName());
            field.setLabel(fieldDTO.getLabel());
            field.setType(fieldDTO.getType());
            field.setDefaultValue(fieldDTO.getDefaultValue());
            field.setForm(form);
            form.getFields().add(field);
        });

        return formRepository.save(form);
    }

    @Override
    public Form updateForm(Long id, FormDTO formDTO) {
        Form form = getFormById(id);
        form.setName(formDTO.getName());
        form.setSubmissionEndpoint(formDTO.getSubmissionEndpoint());

        if (formDTO.getFields() != null) {
            form.getFields().clear();
            formDTO.getFields().forEach(fieldDTO -> {
                Field field = new Field();
                field.setName(fieldDTO.getName());
                field.setLabel(fieldDTO.getLabel());
                field.setType(fieldDTO.getType());
                field.setDefaultValue(fieldDTO.getDefaultValue());
                field.setForm(form);
                form.getFields().add(field);
            });
        }

        return formRepository.save(form);
    }

    @Override
    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }

    @Override
    public void togglePublishStatus(Long id) {
        Form form = getFormById(id);
        form.setPublished(!form.isPublished());
        formRepository.save(form);
    }

    @Override
    public List<Form> getPublishedForms() {
        return formRepository.findByPublished(true);
    }

    @Override
    public List<Field> getFieldsByFormId(Long formId) {
        Form form = getFormById(formId);
        return form.getFields();
    }

    @Override
    public Form updateFieldsByFormId(Long formId, List<FieldDTO> fieldDTOs) {
        Form form = getFormById(formId);

        form.getFields().clear();

        fieldDTOs.forEach(fieldDTO -> {
            Field field = new Field();
            field.setName(fieldDTO.getName());
            field.setLabel(fieldDTO.getLabel());
            field.setType(fieldDTO.getType());
            field.setDefaultValue(fieldDTO.getDefaultValue());
            field.setForm(form);
            form.getFields().add(field);
        });

        return formRepository.save(form);
    }
}
