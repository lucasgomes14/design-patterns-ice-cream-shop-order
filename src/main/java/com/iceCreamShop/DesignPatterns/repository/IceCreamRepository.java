package com.iceCreamShop.DesignPatterns.repository;

import com.iceCreamShop.DesignPatterns.factory.IceCream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IceCreamRepository extends JpaRepository<IceCream, Long> {
}
