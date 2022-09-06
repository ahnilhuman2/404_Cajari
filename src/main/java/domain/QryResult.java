package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QryResult {
	int count;   // 결과값 (정수)
	String status;   // 결과 메세지
	
	@JsonIgnore   // 해당 필드는 json 변환 안됨.
	String memo;
	
//	public String getAddress() {
//		return "서울 광진구";
//	}
}  // end class
