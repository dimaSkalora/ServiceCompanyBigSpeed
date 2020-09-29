package org.speed.big.company.service.model.propertyeditor;

import org.speed.big.company.service.model.Role;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class RolePropertyEditor extends PropertyEditorSupport {

    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        Role role = (Role) getValue(); //getValue Получает значение свойства.
        if (role != null)
            System.out.println("RolePropertyEditor getAsText "+role.toString());
        return role == null ? "" : String.valueOf(role.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            Role role = new Role();
            role.setId(Integer.parseInt(text));
            System.out.println("RolePropertyEditor setAsText "+text);
            setValue(role);
        }
    }
}
