package movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
public class CustomerDTO {
	int custNo;
	String custName;
	String custPhone;
	int custPoint;
}
