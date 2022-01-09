package tf.springadmindemo.wagecalculator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tf.springadmindemo.entity.Tax;

import java.math.BigDecimal;
import java.util.Optional;

public interface TaxRepository extends MongoRepository<Tax, String> {

    Optional<Tax> findByLevel(Long level);

    Optional<Tax> findByMaxValueGreaterThanEqualAndMinValueLessThanEqual(BigDecimal value1, BigDecimal value2);
}
