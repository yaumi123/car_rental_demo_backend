package com.feifei.carrentalprototype.service;

import com.feifei.carrentalprototype.model.CarModel;

import java.util.List;

public interface CarModelService {

  List<CarModel> getModelList();

  CarModel getCarModel(String id);

  void changeStorage(String id, int num);
}
