package tf.springadmindemo.wagecalculator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tf.springadmindemo.entity.Tax;

import java.math.BigDecimal;
import java.util.Optional;

public interface TaxRepository extends MongoRepository<Tax, String> {

    Optional<Tax> findByMaxValueGreaterThanEqualAndMinValueLessThanEqual(BigDecimal value1, BigDecimal value2);
}
