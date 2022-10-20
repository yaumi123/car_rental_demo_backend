package com.feifei.carrentalprototype.service.impl;

import com.feifei.carrentalprototype.model.CarModel;
import com.feifei.carrentalprototype.repository.CarModelRepository;
import com.feifei.carrentalprototype.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelServiceImpl implements CarModelService {
  @Autowired CarModelRepository carModelRepository;

  @Override
  public List<CarModel> getModelList() {
    return carModelRepository.findAll();
  }

  @Override
  public CarModel getCarModel(String id) {
    Optional<CarModel> carModelOptional = carModelRepository.findById(id);
    if (!carModelOptional.isPresent()) {
      throw new RuntimeException("Car model doesn't exist!");
    }
    return carModelOptional.get();
  }

  @Override
  synchronized public void changeStorage(String id, int num) {
    CarModel carModel = this.getCarModel(id);
    int newNum = carModel.getNum() + num;
    if (newNum < 0) {
      throw new RuntimeException("Storage cannot be less than zero!");
    }
    carModel.setNum(newNum);
    carModelRepository.save(carModel);
  }
}
