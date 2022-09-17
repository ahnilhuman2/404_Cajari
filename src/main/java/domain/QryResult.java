package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QryResult {
	int count; // DML의 결과값
	String status; // 결과 메시지
	@JsonIgnore
	String memo;
}