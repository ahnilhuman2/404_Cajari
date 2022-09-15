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
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String name;
    private String authorities;
    private String car_name;
    private String car_number;
    private String phone_number;
    private LocalDateTime regDate;
    
    public String getRegDateTime() {
        if(this.regDate == null) return "";
        return this.regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));    
    }  

} // end DTO






