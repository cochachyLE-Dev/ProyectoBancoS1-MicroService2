package pe.com.bootcamp.domain.aggregate;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Loans")
@Data
public class Loan {
	private String accountNumber;
	private String clientIdentNum;
}
