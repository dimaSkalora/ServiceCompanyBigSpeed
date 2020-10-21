package org.speed.big.company.service.model.propertyeditor.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFGroup;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class WFGroupPropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        WFGroup wfGroup = (WFGroup) getValue(); //getValue Получает значение свойства.
        if (wfGroup != null)
            System.out.println("WFGroupPropertyEditor getAsText "+wfGroup.toString());
        return wfGroup == null ? "" : String.valueOf(wfGroup.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            WFGroup wfGroup = new WFGroup();
            wfGroup.setId(Integer.parseInt(text));
            System.out.println("WFGroupPropertyEditor setAsText "+text);
            setValue(wfGroup);
        }
    }
}
