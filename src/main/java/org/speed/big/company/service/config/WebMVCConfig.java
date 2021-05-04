package org.speed.big.company.service.config;

import org.speed.big.company.service.web.json.JacksonObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

/*    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        //CharacterEncodingFilter - это фильтр сервлета, который помогает нам указать кодирование символов для запросов и ответов.
        //Этот фильтр полезен, когда браузеры не устанавливают кодирование символов или если мы хотим конкретной интерпретации запросов и ответов.
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);//атрибут forceEncoding для обеспечения соблюдения кодирования независимо от его присутствия в запросе из браузера

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }*/

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
       /* ObjectMapper objectMapper = jsonConverter.getObjectMapper();
        objectMapper.registerModule(new Hibernate5Module());  */
        jsonConverter.setObjectMapper(JacksonObjectMapper.getMapper());

        return jsonConverter;
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        stringHttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.TEXT_HTML,MediaType.TEXT_PLAIN));

        return stringHttpMessageConverter;
    }

/*    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)  {
        StringHttpMessageConverter stringHttpMessageConverter =
                //new StringHttpMessageConverter(Charset.forName("UTF-8"));
                new StringHttpMessageConverter();
        MediaType textHtml = new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"));
        MediaType textPlain = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        stringHttpMessageConverter.setSupportedMediaTypes(List.of(textHtml,textPlain));

        converters.add(stringHttpMessageConverter);
        //converters.add(mappingJackson2HttpMessageConverter);
    }*/

    /**
     *    Localization (l10n)            -  реализация в коде
     *    Internationalization  (i18n)   - включение в приложение поддержки разных языков
     */
    //Работает с локалью и хранить в обределеной области допустим Cookie
    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver()  {
        CookieLocaleResolver resolver= new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.UK);
        return resolver;
    }
/*    //Работает с локалью и хранить в обределеной области допустим Session
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.UK);
        return  localeResolver;
    }*/

    @Bean(name = "messageSource")
    public MessageSource getMessageResource()  {
        //ReloadableResourceBundleMessageSource - поиск в любой папке
        //ResourceBundleMessageSource - поиск в класпасе (.classpath)
        ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();
        //messageResource.setBasename("/locales/messages");
        messageResource.setBasename("classpath:/locales/messages");
        messageResource.setDefaultEncoding("UTF-8");
        messageResource.setCacheSeconds(5);

        return messageResource;
    }

    //Перехватывает переключение Locale
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }
}
