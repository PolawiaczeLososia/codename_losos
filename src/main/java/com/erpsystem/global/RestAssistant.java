package com.erpsystem.global;

import com.erpsystem.exception.ObjectNotFoundException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class RestAssistant {

  private RestAssistant(){
    throw new AssertionError();
  }

  public static <T> T checkIfResourceExists(T object, String id){
    if (object == null) {
      throw new ObjectNotFoundException(String.format("Resource with id: %s does not exist", id));
    }else{
      return object;
    }
  }

  public static class ResourceBuilder<T> {

    private final Resource<T> resource;

    public ResourceBuilder(T object, String id){
      if (object == null) {
        throw new ObjectNotFoundException(String.format("Resource with id: %s does not exist", id));
      } else {
        this.resource = new Resource<>(object);
      }
    }

    public ResourceBuilder link(Link link){
      resource.add(link);
      return this;
    }

    public Resource<T> build(){
      return resource;
    }
  }
}
