package pe.com.bootcamp.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import pe.com.bootcamp.domain.aggregate.Loan;
import pe.com.bootcamp.utilities.ResultBase;
import pe.com.bootcamp.utilities.UnitResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LoanRepository implements ILoanRepository {

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	@Override
	public Mono<UnitResult<Loan>> create(Loan entity) {
		Mono<UnitResult<Loan>> result;
		try {
			Query query = new Query(Criteria.where("accountNumber").is(entity.getAccountNumber())); 
			result = mongoTemplate.exists(query, Loan.class).flatMap(i->
			{								
				if(!i.booleanValue())
					return mongoTemplate.insert(entity).map(ii-> new UnitResult<Loan>(ii));				
				else
					return Mono.just(new UnitResult<Loan>(true,"exists loan!"));					
			});
		}catch (Exception e) {
			result = Mono.just(new UnitResult<Loan>(true,e.getMessage()));
		}
		return result;
	}

	@Override
	public Mono<UnitResult<Loan>> update(Loan entity) {
		Mono<UnitResult<Loan>> result;
		try {			
			Query query = new Query(Criteria.where("accountNumber").is(entity.getAccountNumber())); 
			result = mongoTemplate.exists(query, Loan.class).flatMap(i->
			{								
				if(i.booleanValue())
					return mongoTemplate.save(entity).map(ii-> new UnitResult<Loan>(ii));				
				else
					return Mono.just(new UnitResult<Loan>(true,"bank account not exists!"));					
			});
		}catch (Exception e) {
			result = Mono.just(new UnitResult<Loan>(true,e.getMessage()));
		}
		return result;
	}

	@Override
	public Mono<UnitResult<Loan>> saveAll(Flux<Loan> entities) {
		Mono<UnitResult<Loan>> result;
		try {
			result = mongoTemplate.insertAll(entities.collectList(), Loan.class).collectList().map(i-> new UnitResult<Loan>(i));
		}catch (Exception e) {
			result = Mono.just(new UnitResult<Loan>(true,e.getMessage()));
		}
		return result;
	}

	@Override
	public Mono<UnitResult<Loan>> findById(String id) {
		Mono<UnitResult<Loan>> result;
		try {
			result = mongoTemplate.findById(id, Loan.class).map(i-> new UnitResult<Loan>(i));
		} catch (Exception e) {
			result = Mono.just(new UnitResult<Loan>(true,e.getMessage()));
		}
		return result;
	}

	@Override
	public Mono<UnitResult<Loan>> findByClientIdentNum(String dni) {
		Mono<UnitResult<Loan>> result;			
		try {			
			Query query = new Query(Criteria.where("clientIdentNum").is(dni));
			result = mongoTemplate.find(query, Loan.class).collectList().map(i-> new UnitResult<Loan>(i));
		} catch (Exception e) {
			result = Mono.just(new UnitResult<Loan>(true,e.getMessage()));
		}
		return result;
	}

	@Override
	public Mono<UnitResult<Loan>> findByAccountNumber(String accountNumber) {
		Mono<UnitResult<Loan>> result;			
		try {			
			Query query = new Query(Criteria.where("accountNumber").is(accountNumber));			
			result = mongoTemplate.findOne(query, Loan.class).map(i-> new UnitResult<Loan>(i));
		} catch (Exception e) {
			result = Mono.just(new UnitResult<Loan>(true,e.getMessage()));
		}
		return result;
	}

	@Override
	public Mono<UnitResult<Loan>> findAll() {
		Mono<UnitResult<Loan>> result;			
		try {						
			result = mongoTemplate.findAll(Loan.class).collectList().map(i-> new UnitResult<Loan>(i));
		} catch (Exception e) {
			result = Mono.just(new UnitResult<Loan>(true,e.getMessage()));
		}
		return result;
	}

	@Override
	public Mono<ResultBase> deleteById(String id) {
		Mono<ResultBase> result;			
		try {
			Query query = new Query(Criteria.where("Id").is(id));
			result = mongoTemplate.remove(query,Loan.class).flatMap(i-> Mono.just(new ResultBase(i.getDeletedCount() > 0, null)));
		}catch (Exception e) {
			result = Mono.just(new ResultBase(true,e.getMessage()));
		}
		return result;
	}
}
