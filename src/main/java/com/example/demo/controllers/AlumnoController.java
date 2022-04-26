package com.example.demo.controllers;

import java.util.ArrayList;

import com.example.demo.models.AlumnoModel;
import com.example.demo.services.AlumnoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    
    @Autowired
    AlumnoService alumnoService;

    @GetMapping()
    public ArrayList<AlumnoModel> obtenerAlumnos(){
        return alumnoService.obtenerAlumnos();
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> guardarAlumnos(@RequestBody AlumnoModel alumno){
        if(alumno.getId().equals(0) || alumno.getNombres().equals("") || alumno.getApellidos().equals(null)
            ||alumno.getMatricula().matches("[+-]?\\d*(\\.\\d+)?") || alumno.getPromedio()<0){
                return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }else{
                return new ResponseEntity<>(this.alumnoService.guardarAlumno(alumno), HttpStatus.CREATED);
        }
    }

    @GetMapping( path = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> obtenerAlumnosPorId(@PathVariable("id") int id) {
        boolean respuesta = this.alumnoService.obtenerPorId(id).isEmpty();
        if(respuesta){
            return new ResponseEntity<>("{}", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(this.alumnoService.obtenerPorId(id), HttpStatus.OK);
        }
    }

    @DeleteMapping( path = "/{id}")
    public ResponseEntity<Object> eliminarPorId(@PathVariable("id") int id) {
        boolean ok = this.alumnoService.eliminarAlumno(id);
        if (ok){
            return new ResponseEntity<>("Se elimino el alumno " + id, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No se pudo eliminar el alumno " + id, HttpStatus.NOT_FOUND);    
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
     public ResponseEntity<Object> modificarAlumno(@RequestBody AlumnoModel alumno, @PathVariable int id){
        //return this.alumnoService.guardarAlumno(alumno);
        alumno.setId(id);
        System.out.println(alumno.getId()+"Entro Put");

        if(alumno.getNombres()== null || alumno.getMatricula().matches("[+-]?\\d*(\\.\\d+)?")){
                return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(this.alumnoService.modificarAlumno(alumno), HttpStatus.OK);
            }
    }

}