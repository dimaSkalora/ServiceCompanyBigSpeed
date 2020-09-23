package org.speed.big.company.service.model.propertyeditor;

import org.speed.big.company.service.model.RoleType;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class RoleTypePropertyEditor extends PropertyEditorSupport {

    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        RoleType roleType = (RoleType) getValue(); //getValue Получает значение свойства.
        if (roleType != null)
            System.out.println("RoleTypePropertyEditor getAsText "+roleType.toString());
        return roleType == null ? "" : String.valueOf(roleType.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            RoleType roleType = new RoleType();
            roleType.setId(Integer.parseInt(text));
            System.out.println("RoleTypePropertyEditor setAsText "+text);
            setValue(roleType);
        }
    }
}
