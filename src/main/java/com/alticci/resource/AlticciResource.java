package com.alticci.resource;


import com.alticci.dto.ElementDTO;
import com.alticci.service.AlticciService;
import io.quarkus.resteasy.reactive.links.InjectRestLinks;
import io.quarkus.resteasy.reactive.links.RestLink;
import io.quarkus.resteasy.reactive.links.RestLinkType;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.Cache;
import org.jboss.resteasy.reactive.common.util.RestMediaType;

@Path("/alticci")
public class AlticciResource {
  @Inject
  private AlticciService alticciService;


  @GET
  @Path("/{index}")
  @RestLink(rel = "self")
  @Cache(maxAge = 10, sMaxAge = 10)
  @InjectRestLinks(RestLinkType.INSTANCE)
  @Produces({ MediaType.APPLICATION_JSON, RestMediaType.APPLICATION_HAL_JSON })
  public ElementDTO getElementIndex(Integer index){
    var value = alticciService.getElement(index);
    return new ElementDTO(index,value);
  }

}
