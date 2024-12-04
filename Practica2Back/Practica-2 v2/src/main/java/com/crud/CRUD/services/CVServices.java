package com.crud.CRUD.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUD.models.CVModel;
import com.crud.CRUD.repositories.ICVRepository;

@Service
public class CVServices {
    
    @Autowired
    ICVRepository cvRepository;

    public ArrayList<CVModel> getCV(){
        return (ArrayList<CVModel>) cvRepository.findAll();
    }

    public CVModel saveCV(CVModel cv){
        return cvRepository.save(cv);
    }

    public Optional<CVModel> getById(Long id){
        return cvRepository.findById(id);
    }

    public CVModel updateById(CVModel request, Long id){
        CVModel cv = cvRepository.findById(id).get();
        cv.setIdCliente(request.getIdCliente());
        cv.setIdPelicula(request.getIdPelicula());
        cv.setFecha(request.getFecha());
        cvRepository.save(cv);

        return cv;
    }

    public Boolean deleteCVById(Long id){
        try {
            cvRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
