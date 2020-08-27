package org.jamcult.shortsemester.repository;

import org.jamcult.shortsemester.model.House;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends CrudRepository<House, Integer> {
}
