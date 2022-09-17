package domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QryCommentList extends QryResult {

	@JsonProperty("data") // json으로 매핑될 property name // list를 data란 이름으로
	List<QnACommentDTO> list;
}