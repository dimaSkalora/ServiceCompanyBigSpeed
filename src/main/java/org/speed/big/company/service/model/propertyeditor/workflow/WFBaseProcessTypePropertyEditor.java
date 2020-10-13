package org.speed.big.company.service.model.propertyeditor.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class WFBaseProcessTypePropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        WFBaseProcessType wfBaseProcessType = (WFBaseProcessType) getValue(); //getValue Получает значение свойства.
        if (wfBaseProcessType != null)
            System.out.println("WFBaseProcessTypePropertyEditor getAsText "+wfBaseProcessType.toString());
        return wfBaseProcessType == null ? "" : String.valueOf(wfBaseProcessType.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            WFBaseProcessType wfBaseProcessType = new WFBaseProcessType();
            wfBaseProcessType.setId(Integer.parseInt(text));
            System.out.println("WFBaseProcessTypePropertyEditor setAsText "+text);
            setValue(wfBaseProcessType);
        }
    }
}

