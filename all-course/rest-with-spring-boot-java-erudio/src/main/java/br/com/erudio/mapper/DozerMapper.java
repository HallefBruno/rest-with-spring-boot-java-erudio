package br.com.erudio.mapper;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class DozerMapper {
  
  private static final Mapper mapper = new DozerBeanMapper();
  
  public static <O,D> D parseObject(O origem, Class<D> destination) {
    return mapper.map(origem, destination);
  }
  
  public static <O,D> List<D> parseListObjects(List<O> origem, Class<D> destination) {
    List<D> destinationObjects = new ArrayList<>();
    origem.forEach(origim -> {
      destinationObjects.add(mapper.map(origim, destination));
    });
    return destinationObjects;
  }
  
}
