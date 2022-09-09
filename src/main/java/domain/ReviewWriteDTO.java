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
public class ReviewWriteDTO {
	private int r_id; // t404_review_write id
	private UserDTO user; // t404_user id
	private ParkingDTO parking; // t404_parkinglot
	private String subject; // t404_review_write subject
	private String content; // t404_review_write content
	private int viewcnt; // t404_review_write viewcnt
	private LocalDateTime r_regDate; // t404_review_write regdate
	
	public String getRegDateTime() {
		if(this.r_regDate == null) return "";
		return this.r_regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
