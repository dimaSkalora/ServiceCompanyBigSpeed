package org.speed.big.company.service.model.propertyeditor.workflow;

import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class WFPackagePropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        WFPackage wfPackage = (WFPackage) getValue(); //getValue Получает значение свойства.
        if (wfPackage != null)
            System.out.println("WFPackagePropertyEditor getAsText "+wfPackage.toString());
        return wfPackage == null ? "" : String.valueOf(wfPackage.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            WFPackage wfPackage = new WFPackage();
            wfPackage.setId(Integer.parseInt(text));
            System.out.println("WFPackagePropertyEditor setAsText "+text);
            setValue(wfPackage);
        }
    }
}

