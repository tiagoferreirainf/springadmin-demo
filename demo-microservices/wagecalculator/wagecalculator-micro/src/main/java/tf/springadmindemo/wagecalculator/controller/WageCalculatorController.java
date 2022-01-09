package tf.springadmindemo.wagecalculator.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tf.springadmindemo.wagecalculator.entity.GrossSalary;
import tf.springadmindemo.wagecalculator.entity.NetSalary;
import org.springframework.lang.NonNull;
import tf.springadmindemo.wagecalculator.service.WageService;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class WageCalculatorController {

    private WageService wageService;

    @Autowired
    public WageCalculatorController(WageService wageService) {
        this.wageService = wageService;
    }

    @RequestMapping(value = "calculate/net/yearly", method = RequestMethod.POST)
    public NetSalary getYearly(@RequestBody @NonNull GrossSalary salary) {
        return wageService.getYearlyNetSalary(salary);
    }

    @RequestMapping(value = "calculate/net/monthly", method = RequestMethod.POST)
    public NetSalary getMonthly(@RequestBody @NonNull GrossSalary salary) {
        return wageService.getMonthlyNetSalary(salary);
    }

    @RequestMapping(value = "calculate/net", method = RequestMethod.POST)
    public NetSalary getNet(@RequestBody @NonNull GrossSalary salary) {
        return wageService.getNetSalary(salary);
    }

}
