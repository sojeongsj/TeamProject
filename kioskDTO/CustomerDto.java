package kioskDTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@Builder
public class CustomerDto {

	private int custNo;
	private String custName;
	private String custPhone;
	private int custPoint;
}