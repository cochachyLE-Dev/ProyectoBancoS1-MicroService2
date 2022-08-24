package pe.com.bootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.com.bootcamp.domain.aggregate.Loan;
import pe.com.bootcamp.domain.repository.LoanRepository;
import pe.com.bootcamp.utilities.ResultBase;
import pe.com.bootcamp.utilities.UnitResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Loan")
public class LoanController {
	@Autowired
	LoanRepository loanRepository; 
		
	@RequestMapping(value = "/", method = RequestMethod.POST)
	Mono<UnitResult<Loan>> create(@RequestBody Loan entity){
		return loanRepository.create(entity);
	}
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	Mono<UnitResult<Loan>> update(@RequestBody Loan entity){
		return loanRepository.update(entity);
	}
	@RequestMapping(value = "/batch", method = RequestMethod.POST)
	Mono<UnitResult<Loan>> saveAll(@RequestBody Flux<Loan> entities){
		return loanRepository.saveAll(entities);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Mono<UnitResult<Loan>> findById(@PathVariable String id){
		return loanRepository.findById(id);
	}
	@RequestMapping(value = "/{dni}", method = RequestMethod.GET)
	Mono<UnitResult<Loan>> findByClientIdentNum(@PathVariable String dni){
		return loanRepository.findByClientIdentNum(dni);
	}
	@RequestMapping(value = "/{accountNumber}", method = RequestMethod.POST)
	Mono<UnitResult<Loan>> findByAccountNumber(@PathVariable String accountNumber){
		return loanRepository.findByAccountNumber(accountNumber);
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	Mono<UnitResult<Loan>> findAll(){
		return loanRepository.findAll();
	}	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	Mono<ResultBase> deleteById(@PathVariable String id){
		return loanRepository.deleteById(id);
	}
}
