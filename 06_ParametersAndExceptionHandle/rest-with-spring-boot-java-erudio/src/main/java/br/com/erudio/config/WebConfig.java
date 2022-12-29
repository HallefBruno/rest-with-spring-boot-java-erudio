package br.com.erudio.config;

import br.com.erudio.serialization.converter.YamlJackson2HttpMessageConverter;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  
  
  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new YamlJackson2HttpMessageConverter());
  }
  
  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer
    .favorParameter(false)
    .ignoreAcceptHeader(false)
    .useRegisteredExtensionsOnly(false)
    .defaultContentType(MediaType.APPLICATION_JSON)
    .mediaType("json",MediaType.APPLICATION_JSON)
    .mediaType("xml",MediaType.APPLICATION_XML);
  }
  
}
