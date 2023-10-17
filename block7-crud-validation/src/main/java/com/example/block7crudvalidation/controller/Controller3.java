package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.ServiceImpl.StudentServiceImpl;
import com.example.block7crudvalidation.controller.DTO.Inputs.StudentInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.StudentOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.StudentOutputDto2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Student")
public class Controller3 {
    @Autowired
    StudentServiceImpl StudentService;

    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent(@RequestBody String json) throws Exception {
        ObjectMapper om = new ObjectMapper();
        StudentInputDto Student = om.readValue(json,StudentInputDto.class);
        System.out.println(Student.getId_student());

        URI location = URI.create("/Student");
        return ResponseEntity.created(location).body(StudentService.addStudent(Student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputDto> getStudentById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(StudentService.getStudentById(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<StudentOutputDto>> getStudentByName(@PathVariable String nombre){
        try{
            return ResponseEntity.ok().body(StudentService.getStudentByName(nombre));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable int id) {
        try {
            StudentService.deleteStudentById(id);
            return ResponseEntity.ok().body("Student with id: " + id + " was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }
    @GetMapping
    public Iterable<StudentOutputDto2> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return StudentService.getAllStudents(pageNumber, pageSize);
    }

    @GetMapping("/addAsignaturas/{id}")
    public StudentOutputDto addAsignatura(@PathVariable int id,@RequestBody List<Integer> lista){
        return StudentService.addAsignatura(id,lista);
    }


    @PutMapping(value="/{id}")
    public ResponseEntity<StudentOutputDto> updateStudent(@PathVariable int id,@RequestBody StudentInputDto Studentaux) {
        try {
            ResponseEntity<StudentOutputDto> p = this.getStudentById(id);
            StudentOutputDto per = p.getBody();
            StudentInputDto Student = new StudentInputDto(per.getId_student(),
                    per.getId_persona().getId_persona(),
                    per.getNum_hours_week(),
                    per.getId_profesor().getId_profesor(),
                    per.getBranch()
                    );

            if(Studentaux!= null){

                if(Studentaux.getNum_hours_week()!=null){
                    Student.setNum_hours_week(Studentaux.getNum_hours_week());
                }
                if(Studentaux.getId_profesor()!=null){
                    Student.setId_profesor(Studentaux.getId_profesor());
                }
                if(Studentaux.getBranch()!=null){
                    Student.setBranch(Studentaux.getBranch());
                }

            }
            StudentService.updateStudent(Student);
            return  ResponseEntity.ok().body(StudentService.addStudent(Student));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
