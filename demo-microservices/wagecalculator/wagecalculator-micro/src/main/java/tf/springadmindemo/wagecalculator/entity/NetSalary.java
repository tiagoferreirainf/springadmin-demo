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
public class NetSalary implements Serializable {
    private BigDecimal monthlySalary;
    private BigDecimal yearlySalary;
}

