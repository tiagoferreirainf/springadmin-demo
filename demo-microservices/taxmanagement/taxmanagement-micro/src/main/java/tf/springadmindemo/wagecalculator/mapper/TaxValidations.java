package tf.springadmindemo.wagecalculator.mapper;

import java.math.BigDecimal;
import java.util.List;
import tf.springadmindemo.entity.Tax;

public class TaxValidations {

    public static boolean taxIsValidCreateOperation(Tax tax){
        if(tax == null){
            return false;
        }

        if(tax.getLevel() < 0){
            return false;
        }

        if(invalidDecimal(tax.getMaxValue())){
            return false;
        }

        if(invalidDecimal(tax.getMinValue())){
            return false;
        }

        if(tax.getMinValue().compareTo(tax.getMaxValue()) > 0){
            return false;
        }

        if(invalidDecimal(tax.getTax())){
            return false;
        }
        return tax.getTax().compareTo(new BigDecimal(0.0f)) > 0;
    }


    public static boolean taxIsValidCreateOperation(List<Tax> taxList){
        for(Tax tax : taxList){
            if(!taxIsValidCreateOperation(tax)){
                return false;
            }
        }
        return true;
    }

    private static boolean invalidDecimal(BigDecimal decimal){
        Long value = decimal.longValue();
        return value == null || value < 0;
    }
}
