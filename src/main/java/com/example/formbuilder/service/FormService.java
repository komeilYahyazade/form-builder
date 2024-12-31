package com.example.formbuilder.service;

import com.example.formbuilder.model.Field;
import com.example.formbuilder.dto.FieldDTO;

import com.example.formbuilder.dto.FormDTO;
import com.example.formbuilder.model.Form;

import java.util.List;

public interface FormService {
    List<Form> getAllForms();
    Form getFormById(Long id);
    Form createForm(FormDTO formDTO);
    Form updateForm(Long id, FormDTO formDTO);
    void deleteForm(Long id);
    void togglePublishStatus(Long id);
    List<Form> getPublishedForms();
    List<Field> getFieldsByFormId(Long formId);
    Form updateFieldsByFormId(Long formId, List<FieldDTO> fieldDTOs);
}
