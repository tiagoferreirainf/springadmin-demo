package tf.springadmindemo.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Document("tax")
public class Tax implements Serializable {
    @Indexed(unique = true)
    private Long level;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal minValue;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal maxValue;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal tax;
}

