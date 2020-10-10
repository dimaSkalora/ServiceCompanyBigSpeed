package org.speed.big.company.service.model.propertyeditor.workflow;

import org.speed.big.company.service.model.workflow.WFService;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class WFServicePropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        WFService wfService = (WFService) getValue(); //getValue Получает значение свойства.
        if (wfService != null)
            System.out.println("WFServicePropertyEditor getAsText "+wfService.toString());
        return wfService == null ? "" : String.valueOf(wfService.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            WFService wfService = new WFService();
            wfService.setId(Integer.parseInt(text));
            System.out.println("WFServicePropertyEditor setAsText "+text);
            setValue(wfService);
        }
    }
}

