package org.speed.big.company.service.web.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * <p>
 * Handling Hibernate lazy-loading
 *
 * @link https://github.com/FasterXML/jackson
 * @link https://github.com/FasterXML/jackson-datatype-hibernate
 * @link http://wiki.fasterxml.com/JacksonHowToCustomSerializers
 */
public class JacksonObjectMapper extends ObjectMapper {

    private static final ObjectMapper MAPPER = new JacksonObjectMapper();

    private JacksonObjectMapper(){
        //Чтобы Jackson мог сериализовать ленивые объекты, надо добавить Hibernate5Module
        registerModule(new Hibernate5Module());

        //Для нормального отображение LocalDateTime
        registerModule(new JavaTimeModule());
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //Все Типы, NONE
        setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        //Тип поля, модификаторов доступа(ANY) - от частного к общедоступному.
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        //Игнорировать null поля при сериализации
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //Игнорировать пустые поля
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

    }

    public static ObjectMapper getMapper(){
        return MAPPER;
    }
}
