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
   private int id; // id
   private UserDTO user; // user_id
   private String subject; // subject
   private String content; // content
   private int viewcnt; // viewcnt
   private LocalDateTime regDate; // regdate
   
   public String getRegDateTime() {
      if(this.regDate == null) return "";      
      return this.regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
   }
}