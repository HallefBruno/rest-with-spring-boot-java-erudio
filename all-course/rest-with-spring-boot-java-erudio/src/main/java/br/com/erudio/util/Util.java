package br.com.erudio.util;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class Util {
  
  public boolean isNumeric(String strNumber) {
    if(Objects.isNull(strNumber)) return false;
    String number = strNumber.replaceAll(",", ".");
    return number.matches("[-+]?[0-9]*\\.?[0-9]+");
  }
  
  public Double parseStr(String strNumber) {
    if(Objects.isNull(strNumber)) return 0D;
    String number = strNumber.replaceAll(",", ".");
    if(isNumeric(number)) return Double.valueOf(number);
    return 0D;
  }
  
  private static String[] ignorePropertiesNullAndSelected(Object source, String...propertiesIgnore) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    PropertyDescriptor[] pds = src.getPropertyDescriptors();
    Set<String> emptyNames = new HashSet<>();
    Set<String> ignoreProperties = new HashSet<>();
    for (PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (Objects.isNull(srcValue)) {
        emptyNames.add(pd.getName());
      }
    }
    ignoreProperties.addAll(Arrays.asList(propertiesIgnore));
    ignoreProperties.addAll(emptyNames);
    String[] result = new String[emptyNames.size()];
    return ignoreProperties.toArray(result);
  }

  public static void copyProperties(Object src, Object target, String...propertiesIgnore) {
    BeanUtils.copyProperties(src, target, ignorePropertiesNullAndSelected(src, propertiesIgnore));
  }
  
}
