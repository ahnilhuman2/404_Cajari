package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
    private LocalDateTime regDate;
    private String car_name;
    private String car_number;
    private String phone_number;
   
    // String 으로 리턴하는 getter
    public String getRegDateTime() {
        if(this.regDate == null) return "";
        return this.regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));    
    }  

} // end DTO






