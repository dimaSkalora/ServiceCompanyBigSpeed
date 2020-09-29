package org.speed.big.company.service.model.propertyeditor;

import org.speed.big.company.service.model.User;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class UserPropertyEditor extends PropertyEditorSupport {

    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        User user = (User) getValue(); //getValue Получает значение свойства.
        if (user != null)
            System.out.println("UserPropertyEditor getAsText "+user.toString());
        return user == null ? "" : String.valueOf(user.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text))
            setValue(null);         //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            User user = new User();
            user.setId(Integer.parseInt(text));
            System.out.println("UserPropertyEditor setAsText "+text);
            setValue(user);
        }
    }
}
