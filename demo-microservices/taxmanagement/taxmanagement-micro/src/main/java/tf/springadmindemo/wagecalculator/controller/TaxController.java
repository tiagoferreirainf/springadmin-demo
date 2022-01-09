package tf.springadmindemo.wagecalculator.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tf.springadmindemo.entity.Tax;
import tf.springadmindemo.wagecalculator.mapper.TaxValidations;
import tf.springadmindemo.wagecalculator.repository.TaxRepository;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class TaxController {

    TaxRepository taxRepository;


    @Autowired
    public TaxController( TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    @RequestMapping(value = "get/all", method = RequestMethod.GET)
    public List<Tax> getAllTaxs() {
        log.info("Getting all Taxs.");
        return taxRepository.findAll();
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Tax addNewTaxs(@RequestBody @NonNull Tax tax) throws Exception {
        boolean isValid = TaxValidations.taxIsValidCreateOperation(tax);
        if(!isValid){
            throw new Exception("Invalid Tax Entity");
        }

        log.info("Saving Tax:  {}", tax.toString());
        return taxRepository.save(tax);
    }

    @RequestMapping(value = "/createAll", method = RequestMethod.POST)
    public List<Tax> addNewTaxs(@RequestBody @NonNull List<Tax> taxList) throws Exception {
        boolean isValid = TaxValidations.taxIsValidCreateOperation(taxList);
        if(!isValid){
            throw new Exception("Invalid Tax Entity");
        }

        log.info("Saving Tax List: ");
        return taxRepository.saveAll(taxList);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public Tax getById(@PathVariable("id") @NonNull Long id) throws Exception {
        log.info("Get Tax by id {} ", id);

        boolean isValid = id != null;
        if(!isValid){
            throw new Exception("Invalid Tax level");
        }

        Optional<Tax> byId = taxRepository.findByLevel(id);
        return byId.orElse(null);
    }

    @RequestMapping(value = "/get/range/{value}", method = RequestMethod.GET)
    public Tax getByRange(@PathVariable("value") @NonNull BigDecimal value) throws Exception {
        log.info("Get Tax by id {} ", value);

        boolean isValid = value != null;
        if(!isValid){
            throw new Exception("Invalid Tax level");
        }

        return taxRepository.findByMaxValueGreaterThanEqualAndMinValueLessThanEqual(value, value).orElse(null);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") @NonNull Long id) throws Exception {
        log.info("Delete Tax by id {}", id);

        boolean isValid = id != null;
        if(!isValid){
            throw new Exception("Invalid Tax Entity");
        }

        Optional<Tax> Tax = taxRepository.findByLevel(id);
        Tax.ifPresent(taxRepository::delete);
    }

    @DeleteMapping(value = "/delete/all")
    public void deleteAll() {
        log.info("Delete All Taxs");
        taxRepository.deleteAll();
    }
}
