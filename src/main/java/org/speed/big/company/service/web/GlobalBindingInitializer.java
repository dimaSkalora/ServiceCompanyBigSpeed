package org.speed.big.company.service.web;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.model.propertyeditor.RolePropertyEditor;
import org.speed.big.company.service.model.propertyeditor.RoleTypePropertyEditor;
import org.speed.big.company.service.model.propertyeditor.UserPropertyEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Глобальный клас который преабразовывает обьэкты в строки и наобород (при передачи параметров)
@ControllerAdvice
public class GlobalBindingInitializer {

    //Method - который преабразовывает обьэкты в строки и наобород (при передачи параметров)
    @InitBinder
    public void initBinder(WebDataBinder binder){
        PropertyEditor editor = new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (!text.trim().isEmpty())
                    super.setValue(LocalDateTime.parse(text.trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            }
            @Override
            public String getAsText() {
                if (super.getValue() == null)
                    return null;
                LocalDateTime value = (LocalDateTime) super.getValue();
                return value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        };

        binder.registerCustomEditor(LocalDateTime.class, editor);
        binder.registerCustomEditor(RoleType.class, new RoleTypePropertyEditor());
        binder.registerCustomEditor(User.class, new UserPropertyEditor());
        binder.registerCustomEditor(Role.class, new RolePropertyEditor());
    }
}
