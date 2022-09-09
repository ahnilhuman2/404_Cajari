package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class QnAWriteDTO {
	private int q_id; // t404_qna_write id
	private UserDTO user; // t404_user id
	private String subject; // t404_qna_write subject
	private String content; // t404_qna_write content
	private int viewcnt; // t404_qna_write viewcnt
	private LocalDateTime q_regDate; // t404_qna_write regdate
	
	public String getRegDateTime() {
		if(this.q_regDate == null) return "";		
		return this.q_regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
