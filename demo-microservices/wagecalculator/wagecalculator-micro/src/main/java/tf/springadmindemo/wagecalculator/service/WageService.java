package tf.springadmindemo.wagecalculator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tf.springadmindemo.entity.Tax;
import tf.springadmindemo.wagecalculator.entity.GrossSalary;
import tf.springadmindemo.wagecalculator.entity.NetSalary;
import tf.springadmindemo.wagecalculator.repository.TaxRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@Slf4j
public class WageService {

    private Integer numberOfMonths = 12;
    private Integer numberOfBonusMonths = 2;
    private BigDecimal socialSecurityTax = new BigDecimal(11.0f);

    private TaxRepository taxRepository;

    @Autowired
    public WageService(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }


    public NetSalary getNetSalary(GrossSalary gross) {
        log.info("GrossSalary to calculate {}", gross.toString());
        boolean calculateBasedOnMonthlyEstimates = false;
        BigDecimal yearlySalary = gross.getYearlySalary();
        if (yearlySalary == null || yearlySalary.floatValue() == 0) {
            calculateBasedOnMonthlyEstimates = true;
        }

        BigDecimal monthlySalary = gross.getMonthlySalary();
        if (monthlySalary == null) {
            monthlySalary = new BigDecimal(0.0f);
        }

        if (calculateBasedOnMonthlyEstimates) {
            yearlySalary = monthlySalary.multiply(new BigDecimal(numberOfMonths + numberOfBonusMonths));
        }

        BigDecimal yearlyBonus = gross.getYearlyBonus();
        if (yearlyBonus == null) {
            yearlyBonus = new BigDecimal(0.0f);
        }

        BigDecimal monthlyBonus = gross.getMonthlyBonus();
        if (monthlyBonus == null) {
            monthlyBonus = new BigDecimal(0.0f);
        }

        BigDecimal totalYearly = yearlySalary.add(yearlyBonus).add(new BigDecimal(numberOfMonths).multiply(monthlyBonus));

        Optional<Tax> byRange = taxRepository.findByMaxValueGreaterThanEqualAndMinValueLessThanEqual(totalYearly, totalYearly);
        if (byRange.isEmpty()) {
            return null;
        }

        BigDecimal tax = byRange.get().getTax();

        BigDecimal totalYearlyNet = yearlySalary.subtract(getPercentValue(yearlySalary, tax.add(socialSecurityTax)));
        BigDecimal totalYearlyBonuxNet = new BigDecimal(0.0f);
        if (yearlyBonus.floatValue() > 0) {
            totalYearlyBonuxNet = yearlyBonus.subtract(getPercentValue(yearlyBonus, tax));
        }

        BigDecimal totalNet = totalYearlyBonuxNet.add(totalYearlyNet);
        BigDecimal totalNetMonthly = totalNet.divide(new BigDecimal(numberOfMonths + numberOfBonusMonths), 2, RoundingMode.HALF_UP);

        NetSalary net = NetSalary.builder().yearlySalary(totalNet).monthlySalary(totalNetMonthly).build();
        log.info("NetSalary calculated {}", net.toString());
        return net;
    }

    public NetSalary getYearlyNetSalary(GrossSalary gross) {
        NetSalary netSalary = getNetSalary(gross);
        if(netSalary != null) {
            netSalary.setMonthlySalary(null);
        }
        return netSalary;
    }

    public NetSalary getMonthlyNetSalary(GrossSalary gross) {
        NetSalary netSalary = getNetSalary(gross);
        if(netSalary != null) {
            netSalary.setYearlySalary(null);
        }
        return netSalary;
    }

    private BigDecimal getPercentValue(BigDecimal initial, BigDecimal percent) {
        return (initial.multiply(percent).divide(new BigDecimal(100.0f), RoundingMode.UNNECESSARY));
    }

}
