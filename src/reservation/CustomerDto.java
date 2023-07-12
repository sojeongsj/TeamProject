package reservation;

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

	private int CustNo;
	private String CustName;
	private String CustPhone;
	private int CustPoint;
}