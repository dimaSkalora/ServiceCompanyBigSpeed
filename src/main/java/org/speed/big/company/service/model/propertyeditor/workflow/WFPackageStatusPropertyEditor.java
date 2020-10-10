package org.speed.big.company.service.model.propertyeditor.workflow;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class WFPackageStatusPropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        WFPackageStatus wfPackageStatus = (WFPackageStatus) getValue(); //getValue Получает значение свойства.
        if (wfPackageStatus != null)
            System.out.println("WFPackageStatusPropertyEditor getAsText "+wfPackageStatus.toString());
        return wfPackageStatus == null ? "" : String.valueOf(wfPackageStatus.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            WFPackageStatus wfPackageStatus = new WFPackageStatus();
            wfPackageStatus.setId(Integer.parseInt(text));
            System.out.println("WFPackageStatusPropertyEditor setAsText "+text);
            setValue(wfPackageStatus);
        }
    }
}
