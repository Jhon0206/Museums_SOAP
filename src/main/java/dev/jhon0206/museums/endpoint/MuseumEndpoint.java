package dev.jhon0206.museums.endpoint;

import dev.jhon0206.museums.config.WebServiceConfig;
import dev.jhon0206.museums.service.MuseumService;
import dev.jhon0206.museums.soap.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MuseumEndpoint {

    private final MuseumService museumService;

    public MuseumEndpoint(MuseumService service) {
        this.museumService = service;
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "CreateMuseumRequest")
    @ResponsePayload
    public CreateMuseumResponse createMuseum(@RequestPayload CreateMuseumRequest request) {
        return museumService.create(request);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "GetMuseumRequest")
    @ResponsePayload
    public GetMuseumResponse getMuseum(@RequestPayload GetMuseumRequest request) {
        return museumService.getById(request);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "ListMuseumsRequest")
    @ResponsePayload
    public ListMuseumsResponse listMuseums(@RequestPayload ListMuseumsRequest request) {
        return museumService.listAll(request);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "UpdateMuseumRequest")
    @ResponsePayload
    public UpdateMuseumResponse updateMuseum(@RequestPayload UpdateMuseumRequest request) {
        return museumService.update(request);
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "DeleteMuseumRequest")
    @ResponsePayload
    public DeleteMuseumResponse deleteMuseum(@RequestPayload DeleteMuseumRequest request) {
        return museumService.delete(request);
    }
}