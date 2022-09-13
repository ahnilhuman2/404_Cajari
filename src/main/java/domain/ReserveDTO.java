package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveDTO {
    private int id;
    private UserDTO user; 
    private ParkingDTO parking_name;
    private LocalDateTime regDate; 
    private String checkin_time;
    
    public String getRegDateTime() {
		if(this.regDate == null) return "";
		return this.regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
