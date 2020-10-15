package org.speed.big.company.service.model.propertyeditor.workflow;

import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class WFProcessStatusPropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        WFProcessStatus wfProcessStatus = (WFProcessStatus) getValue(); //getValue Получает значение свойства.
        if (wfProcessStatus != null)
            System.out.println("WFProcessStatusPropertyEditor getAsText "+wfProcessStatus.toString());
        return wfProcessStatus == null ? "" : String.valueOf(wfProcessStatus.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            WFProcessStatus wfProcessStatus = new WFProcessStatus();
            wfProcessStatus.setId(Integer.parseInt(text));
            System.out.println("WFProcessStatusPropertyEditor setAsText "+text);
            setValue(wfProcessStatus);
        }
    }
}


