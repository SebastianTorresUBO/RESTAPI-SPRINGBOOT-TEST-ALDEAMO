package springboot.restapitest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.restapitest.model.*;
import springboot.restapitest.repository.ArrayRepository;

@Service
public class ArraysService {
	
	@Autowired
	ArrayRepository arrayRepository;
	
	public ArraysEntity buscarVasosPorID(Long vasid) {
		return arrayRepository.findOne(vasid);
	}
	

}
