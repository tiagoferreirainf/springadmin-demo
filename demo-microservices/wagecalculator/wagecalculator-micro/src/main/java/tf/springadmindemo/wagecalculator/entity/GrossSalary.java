package tf.springadmindemo.wagecalculator.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class GrossSalary implements Serializable {
    private BigDecimal monthlySalary;
    private BigDecimal monthlyBonus;
    private BigDecimal yearlyBonus;
    private BigDecimal yearlySalary;
}

