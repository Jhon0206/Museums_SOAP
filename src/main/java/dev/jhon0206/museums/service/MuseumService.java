package dev.jhon0206.museums.service;

import dev.jhon0206.museums.entity.Museum;
import dev.jhon0206.museums.repository.MuseumRepository;
import dev.jhon0206.museums.soap.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MuseumService {

    private final MuseumRepository museumRepository;

    public MuseumService(MuseumRepository museumRepository) {
        this.museumRepository = museumRepository;
    }

    public CreateMuseumResponse create(CreateMuseumRequest request) {
        Museum entity = new Museum();
        entity.setName(request.getName());
        entity.setCity(request.getCity());
        entity.setAddress(request.getAddress());
        entity.setOpeningHours(request.getOpeningHours());
        entity.setAdmissionPrice(request.getAdmissionPrice());

        Museum saved = museumRepository.save(entity);

        CreateMuseumResponse response = new CreateMuseumResponse();
        response.setMuseum(toMuseumInfo(saved));
        return response;
    }

    @Transactional(readOnly = true)
    public GetMuseumResponse getById(GetMuseumRequest request) {
        Museum museum = museumRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Museum no encontrado con id: " + request.getId()));

        GetMuseumResponse response = new GetMuseumResponse();
        response.setMuseum(toMuseumInfo(museum));
        return response;
    }

    @Transactional(readOnly = true)
    public ListMuseumsResponse listAll(ListMuseumsRequest request) {
        List<MuseumInfo> list = museumRepository.findAll()
                .stream()
                .map(this::toMuseumInfo)
                .toList();

        ListMuseumsResponse response = new ListMuseumsResponse();
        response.getMuseum().addAll(list);
        return response;
    }

    public UpdateMuseumResponse update(UpdateMuseumRequest request) {
        Museum existent = museumRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Museum no encontrado con id: " + request.getId()));

        existent.setName(request.getName());
        existent.setCity(request.getCity());
        existent.setAddress(request.getAddress());
        existent.setOpeningHours(request.getOpeningHours());
        existent.setAdmissionPrice(request.getAdmissionPrice());

        Museum actualizado = museumRepository.save(existent);

        UpdateMuseumResponse response = new UpdateMuseumResponse();
        response.setMuseum(toMuseumInfo(actualizado));
        return response;
    }

    public DeleteMuseumResponse delete(DeleteMuseumRequest request) {
        DeleteMuseumResponse response = new DeleteMuseumResponse();
        if (!museumRepository.existsById(request.getId())) {
            response.setDeleted(false);
            response.setMessage("No existe un museum con id: " + request.getId());
            return response;
        }

        museumRepository.deleteById(request.getId());
        response.setDeleted(true);
        response.setMessage("Museum eliminado correctamente");
        return response;
    }

    private MuseumInfo toMuseumInfo(Museum museum) {
        MuseumInfo info = new MuseumInfo();
        info.setId(museum.getId());
        info.setName(museum.getName());
        info.setCity(museum.getCity());
        info.setAddress(museum.getAddress());
        info.setOpeningHours(museum.getOpeningHours());
        info.setAdmissionPrice(museum.getAdmissionPrice());
        return info;
    }

}
