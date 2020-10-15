package org.speed.big.company.service.model.propertyeditor.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class WFBaseProcessPropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        WFBaseProcess wfBaseProcess = (WFBaseProcess) getValue(); //getValue Получает значение свойства.
        if (wfBaseProcess != null)
            System.out.println("WFBaseProcessPropertyEditor getAsText "+wfBaseProcess.toString());
        return wfBaseProcess == null ? "" : String.valueOf(wfBaseProcess.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            WFBaseProcess wfBaseProcess = new WFBaseProcess();
            wfBaseProcess.setId(Integer.parseInt(text));
            System.out.println("WFBaseProcessPropertyEditor setAsText "+text);
            setValue(wfBaseProcess);
        }
    }
}

