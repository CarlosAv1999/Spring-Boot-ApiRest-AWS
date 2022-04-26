package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.AlumnoModel;
import com.example.demo.repositories.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {
    @Autowired
    AlumnoRepository alumnoRepository;
    
    public ArrayList<AlumnoModel> obtenerAlumnos(){
        return (ArrayList<AlumnoModel>) alumnoRepository.findAll();
    }

    public AlumnoModel guardarAlumno(AlumnoModel alumno){
        return alumnoRepository.save(alumno);
    }

    public AlumnoModel modificarAlumno(AlumnoModel alumno){
        //return alumnoRepository.save(alumno);
        return alumnoRepository.save(alumno);
    }

    public Optional<AlumnoModel> obtenerPorId(Integer id){
        return alumnoRepository.findById(id);
    }

    /*
    public ArrayList<AlumnoModel>  obtenerPorPrioridad(int prioridad) {
        return alumnoRepository.findById(prioridad);
    }
    */

    public boolean eliminarAlumno(Integer id) {
        try{
            alumnoRepository.deleteById(id);
            System.out.println(id);
            //System.out.println("entro trueeeee");
            return true;
        }catch(Exception err){
            //System.out.println("entro falseeeeee");
            System.out.println(id);
            return false;
        }
    }
}
