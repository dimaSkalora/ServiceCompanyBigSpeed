package org.speed.big.company.service.web;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.model.propertyeditor.RolePropertyEditor;
import org.speed.big.company.service.model.propertyeditor.RoleTypePropertyEditor;
import org.speed.big.company.service.model.propertyeditor.UserPropertyEditor;
import org.speed.big.company.service.model.propertyeditor.workflow.*;
import org.speed.big.company.service.model.workflow.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//Глобальный клас
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

        PropertyEditor localDateEditor = new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (!text.trim().isEmpty())
                    super.setValue(LocalDate.parse(text.trim(), DateTimeFormatter.ISO_LOCAL_DATE));
            }
            @Override
            public String getAsText() {
                if (super.getValue() == null)
                    return null;
                LocalDate value = (LocalDate) super.getValue();
                return value.format(DateTimeFormatter.ISO_LOCAL_DATE);
            }
        };

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(LocalDate.class, localDateEditor);
        binder.registerCustomEditor(LocalDateTime.class, editor);

        //jdbc
/*        binder.registerCustomEditor(RoleType.class, new RoleTypePropertyEditor());
        binder.registerCustomEditor(User.class, new UserPropertyEditor());
        binder.registerCustomEditor(Role.class, new RolePropertyEditor());
        binder.registerCustomEditor(WFPackageStatus.class, new WFPackageStatusPropertyEditor());
        binder.registerCustomEditor(WFService.class, new WFServicePropertyEditor());
        binder.registerCustomEditor(WFBaseProcessType.class, new WFBaseProcessTypePropertyEditor());
        binder.registerCustomEditor(WFPackage.class, new WFPackagePropertyEditor());
        binder.registerCustomEditor(WFProcessState.class,new WFProcessStatePropertyEditor());
        binder.registerCustomEditor(WFProcessStatus.class,new WFProcessStatusPropertyEditor());
        binder.registerCustomEditor(WFBaseProcess.class,new WFBaseProcessPropertyEditor());
        binder.registerCustomEditor(WFProcess.class,new WFProcessPropertyEditor());
        binder.registerCustomEditor(WFGroup.class,new WFGroupPropertyEditor());*/
    }
}
