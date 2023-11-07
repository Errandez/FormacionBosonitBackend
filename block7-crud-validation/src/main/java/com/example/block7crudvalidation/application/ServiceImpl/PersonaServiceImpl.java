package com.example.block7crudvalidation.application.ServiceImpl;


import com.example.block7crudvalidation.application.Service.PersonaService;
import com.example.block7crudvalidation.application.UnprocessableEntityException;
import com.example.block7crudvalidation.controller.DTO.Inputs.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Mappers.PersonaMapper;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.repository.PersonaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    PersonaRepository PersonaRepository;



    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws UnprocessableEntityException, Exception {
            Persona p = new Persona(persona);

            return (PersonaRepository.save(p)).personToPersonOutputDto();
    }

    @Override
    public PersonaOutputDto getPersonaById(int id) {
        return (PersonaRepository.findById(id).orElseThrow()).personToPersonOutputDto();
    }

    @Override
    public List<PersonaOutputDto> getPersonaByName(String Name) {
        List<PersonaOutputDto> personas =  PersonaRepository.findAll()
                .stream()
                .map(Persona::personToPersonOutputDto).toList();
        List<PersonaOutputDto> personasFinal = new ArrayList<>();
        for(PersonaOutputDto paux:personas){
            if(paux.getName().equalsIgnoreCase(Name)){
                personasFinal.add(paux);
            }
        }
        return personasFinal;
    }

    @Override
    public void deletePersonaById(int id) {
        PersonaRepository.findById(id).orElseThrow();
        PersonaRepository.deleteById(id);
    }
    @Override
    public List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return PersonaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personToPersonOutputDto).toList();
    }


    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto personaInputDto) throws Exception {
        Persona persona=PersonaRepository.findById(personaInputDto.getId_persona()).orElseThrow();
        PersonaMapper.INSTANCE.updatePersonFromDto(personaInputDto,persona);

        return (persona).personToPersonOutputDto();
    }

    public List<PersonaOutputDto> mayorQue(HashMap<String, Object> data){
        PageRequest pageRequest = PageRequest.of((Integer)data.get("pageNumber"),(Integer)data.get("pageSize"));
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = criteriaBuilder.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicados = new ArrayList<>();
        data.forEach((field,value)->{
            switch(field){
                case "usuario":
                    predicados.add(criteriaBuilder.greaterThan(root.get(field),(String)data.get("usuario")));
                    break;
                case "name":
                    predicados.add(criteriaBuilder.greaterThan(root.get(field),(String)data.get("name")));
                    break;
                case "surname":
                    predicados.add(criteriaBuilder.greaterThan(root.get(field),(String)data.get("surname")));
                    break;
                case "created_date":
                    predicados.add(criteriaBuilder.greaterThan(root.get(field),(Date)data.get("created_date")));
                    break;
                default:
                    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"No puede clasificarse por " + field + ".");
            }
        });
        if(data.get("orden")!=null){
            String seleccionado = "";
            switch ((String) data.get("orden")) {
                case "usuario":
                    seleccionado = "usuario";
                    break;
                case "name":
                    seleccionado = "name";
                    break;
            }
            if(!seleccionado.isEmpty()){
                query.select(root)
                        .where(predicados.toArray(new Predicate[predicados.size()]))
                        .orderBy(data.get("direccion").equals("desc")?criteriaBuilder.desc(root.get(seleccionado)) : criteriaBuilder.asc(root.get(seleccionado)));
            }
        }
        return entityManager
                .createQuery(query).setFirstResult((pageRequest.getPageNumber() * pageRequest.getPageSize())+1).setMaxResults(pageRequest.getPageSize())
                .getResultList()
                .stream()
                .map(Persona::personToPersonOutputDto)
                .toList();
    }

    public List<PersonaOutputDto> menorQue(HashMap<String, Object> data){
        PageRequest pageRequest = PageRequest.of((Integer)data.get("pageNumber"),(Integer)data.get("pageSize"));
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = criteriaBuilder.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicados = new ArrayList<>();
        data.forEach((field,value)->{
            switch(field){
                case "usuario":
                    predicados.add(criteriaBuilder.lessThan(root.get(field),(String)data.get("usuario")));
                    break;
                case "name":
                    predicados.add(criteriaBuilder.lessThan(root.get(field),(String)data.get("name")));
                    break;
                case "surname":
                    predicados.add(criteriaBuilder.lessThan(root.get(field),(String)data.get("surname")));
                    break;
                case "created_date":
                    predicados.add(criteriaBuilder.lessThan(root.get(field),(Date)data.get("created_date")));
                    break;
                default:
                    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"No puede clasificarse por " + field + ".");
            }
        });
        if(data.get("orden")!=null){
            String seleccionado = "";
            switch ((String) data.get("orden")) {
                case "usuario":
                    seleccionado = "usuario";
                    break;
                case "name":
                    seleccionado = "name";
                    break;
            }
            if(!seleccionado.isEmpty()){
                query.select(root)
                        .where(predicados.toArray(new Predicate[predicados.size()]))
                        .orderBy(data.get("direccion").equals("desc")?criteriaBuilder.desc(root.get(seleccionado)) : criteriaBuilder.asc(root.get(seleccionado)));
            }
        }
        return entityManager
                .createQuery(query).setFirstResult((pageRequest.getPageNumber() * pageRequest.getPageSize())+1).setMaxResults(pageRequest.getPageSize())
                .getResultList()
                .stream()
                .map(Persona::personToPersonOutputDto)
                .toList();
    }

}