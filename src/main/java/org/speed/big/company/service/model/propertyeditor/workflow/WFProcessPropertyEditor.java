package org.speed.big.company.service.model.propertyeditor.workflow;

import org.speed.big.company.service.model.workflow.WFProcess;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class WFProcessPropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        WFProcess wfProcess = (WFProcess) getValue(); //getValue Получает значение свойства.
        if (wfProcess != null)
            System.out.println("WFProcessPropertyEditor getAsText "+wfProcess.toString());
        return wfProcess == null ? "" : String.valueOf(wfProcess.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            WFProcess wfProcess = new WFProcess();
            wfProcess.setId(Integer.parseInt(text));
            System.out.println("WFProcessPropertyEditor setAsText "+text);
            setValue(wfProcess);
        }
    }
}
