package com.example.block7crudvalidation;

import com.example.block7crudvalidation.controller.Controller2;
import com.example.block7crudvalidation.controller.DTO.Inputs.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.DTO.Inputs.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.Inputs.ProfesorInputDto;
import com.example.block7crudvalidation.controller.DTO.Inputs.StudentInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaStudentOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaProfesorOutputDto;
import com.example.block7crudvalidation.domain.Mappers.AsignaturaMapper;
import com.example.block7crudvalidation.domain.Mappers.ProfesorMapper;
import com.example.block7crudvalidation.domain.Mappers.StudentMapper;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.repository.AsignaturaRepository;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonaRepository personaRepository;

    @BeforeAll
    public void Starting(){
        Persona persona = new Persona();
        Persona persona2 = new Persona();
        persona.setPersona(1);
        persona2.setPersona(2);
        persona.setName("Erick");
        persona2.setName("Irati");
        persona.setSurname("Randez");
        persona2.setSurname("Arritokieta");
        persona.setUsuario("Randezzz");
        persona2.setUsuario("Arrotikie");
        persona.setPassword("Contraseña");
        persona2.setPassword("Contraseña1");
        personaRepository.save(persona);
        personaRepository.save(persona2);
    }

    @Test
    @DisplayName("Añadir Persona Test")
    public void testAddPersona() throws Exception {

        PersonaInputDto personaInputDto = new PersonaInputDto(3,"Navajasss","contraseña1","Erick1","Randez1","aaa1","aaa1","Alfaro",false,new Date(System.currentTimeMillis()),"",null);
        PersonaInputDto personaInputDto1 = new PersonaInputDto(4,null,"contraseña2","Erick2","Randez2","aaa2","aaa2","Alfaro",false,new Date(System.currentTimeMillis()),"",null);
        PersonaInputDto personaInputDto2 = new PersonaInputDto(5,"Navajassssssss","contraseña3","Erick3","Randez3","aaa3","aaa3","Alfaro",false,new Date(System.currentTimeMillis()),"",null);

        String persona = new ObjectMapper().writeValueAsString(personaInputDto);
        String persona1 = new ObjectMapper().writeValueAsString(personaInputDto1);
        String persona2 = new ObjectMapper().writeValueAsString(personaInputDto2);

        this.mockMvc.perform(post("/person/addperson").contentType(MediaType.APPLICATION_JSON).content(persona)).andExpect(status().is2xxSuccessful()).andReturn();
        this.mockMvc.perform(get("/person/2")).andDo(print()).andExpect(status().isOk()).andReturn();
        try{
            this.mockMvc.perform(post("/person/addperson").contentType(MediaType.APPLICATION_JSON).content(persona1)).andExpect(status().is4xxClientError()).andReturn();
        }catch (HttpClientErrorException e){
            Assertions.assertEquals(e.getMessage(),"Hay valores nulos.");
        }
        try{
            this.mockMvc.perform(post("/person/addperson").contentType(MediaType.APPLICATION_JSON).content(persona2)).andExpect(status().is4xxClientError()).andReturn();
        }catch (HttpClientErrorException e){
            Assertions.assertEquals(e.getMessage(),"Nombre de usuario demasiado largo.");
        }
    }

    @Test
    @DisplayName("Recuperar Personas Test")
    public void getPersonTest() throws Exception{
        MvcResult resultado = this.mockMvc.perform(get("/person/1")).andDo(print()).andExpect(status().is2xxSuccessful()).andReturn();
        String persona = resultado.getResponse().getContentAsString();
        PersonaOutputDto personaOutputDto;
        if(persona.contains("profesorOutputDTO")){
            personaOutputDto = new ObjectMapper().readValue(persona, new TypeReference<PersonaProfesorOutputDto> (){});
        } else if (persona.contains("StudentOutputDto")) {
            personaOutputDto = new ObjectMapper().readValue(persona, new TypeReference<PersonaStudentOutputDto> (){});
        }else {
            personaOutputDto= new ObjectMapper().readValue(persona, new TypeReference<>() {});
        }
        Assertions.assertEquals("Randezzz",personaOutputDto.getUsuario());

        try{
            this.mockMvc.perform(get("/person/5")).andDo(print()).andExpect(status().is4xxClientError()).andReturn();
        }catch(NoSuchElementException e){
            Assertions.assertEquals(e.getMessage(),"Esta persona no existe.");
        }
    }

    @Test
    @DisplayName("Borrar Persona Test")
    public void BorrarPersonaTest() throws Exception {
        this.mockMvc.perform(delete("/person/2")).andDo(print()).andExpect(status().isOk()).andReturn();
        try{
            this.mockMvc.perform(get("/person/2")).andDo(print()).andExpect(status().is4xxClientError()).andReturn();
        }catch (NoSuchElementException e){
            Assertions.assertEquals(e.getMessage(),"No se encuentra la persona.");
        }
        try{
            this.mockMvc.perform(delete("/person/2")).andDo(print()).andExpect(status().is4xxClientError()).andReturn();
        }catch (NoSuchElementException e){
            Assertions.assertEquals(e.getMessage(),"No se encuentra la persona.");
        }
    }


    @Test
    @DisplayName("Actualizar Persona Test")
    public void ActualizaPersonaTest() throws Exception {

        PersonaOutputDto personaOutputDto;
        PersonaInputDto personaInputDto = new PersonaInputDto(null,"Navajasss","contraseña1","Erick1","Randez1","aaa1","aaa1","Alfaro",false,new Date(System.currentTimeMillis()),"",null);
        String persona = new ObjectMapper().writeValueAsString(personaInputDto);
        this.mockMvc.perform(put("/person/1").contentType(MediaType.APPLICATION_JSON).content(persona)).andExpect(status().is2xxSuccessful()).andReturn();
        MvcResult result = this.mockMvc.perform(get("/person/1")).andDo(print()).andExpect(status().isOk()).andReturn();

        if(persona.contains("ProfesorOutputDto")){
            personaOutputDto= new ObjectMapper().readValue(persona, new TypeReference<PersonaStudentOutputDto>() {});
        } else if (persona.contains("StudentOutputDto")) {
            personaOutputDto= new ObjectMapper().readValue(persona, new TypeReference<PersonaProfesorOutputDto>() {});
        }else{
            personaOutputDto= new ObjectMapper().readValue(persona, new TypeReference<>() {});
        }

        Assertions.assertEquals("Navajasss",personaOutputDto.getUsuario());


        try{
            this.mockMvc.perform(put("/person/89")).andDo(print()).andExpect(status().is4xxClientError()).andReturn();
        }catch (NoSuchElementException e){
            Assertions.assertEquals(e.getMessage(),"No se encuentra la persona.");
        }
    }

}
