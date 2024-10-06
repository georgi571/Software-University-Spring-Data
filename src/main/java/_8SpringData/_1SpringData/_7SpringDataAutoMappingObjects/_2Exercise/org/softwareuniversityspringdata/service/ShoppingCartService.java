package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.CartItemDTO;

public interface ShoppingCartService {
    String addItem(CartItemDTO item);
    String deleteItem(CartItemDTO item);
    String buyItem();
}
