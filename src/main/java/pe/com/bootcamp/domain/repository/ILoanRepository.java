package pe.com.bootcamp.domain.repository;

import pe.com.bootcamp.domain.aggregate.Loan;
import pe.com.bootcamp.utilities.ResultBase;
import pe.com.bootcamp.utilities.UnitResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILoanRepository{
	Mono<UnitResult<Loan>> create(Loan entity);
	
	Mono<UnitResult<Loan>> update(Loan entity);
	
	Mono<UnitResult<Loan>> saveAll(Flux<Loan> entities);
	
	Mono<UnitResult<Loan>> findById(String id);
	
	Mono<UnitResult<Loan>> findByClientIdentNum(String dni);
	
	Mono<UnitResult<Loan>> findByAccountNumber(String accountNumber);
	
	Mono<UnitResult<Loan>> findAll();	
	
	Mono<ResultBase> deleteById(String id);
}
