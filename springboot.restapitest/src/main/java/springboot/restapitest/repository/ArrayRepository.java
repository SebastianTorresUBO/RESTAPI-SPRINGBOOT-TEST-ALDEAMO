package springboot.restapitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.restapitest.model.ArraysEntity;

public interface ArrayRepository extends JpaRepository<ArraysEntity,Long> {

}
