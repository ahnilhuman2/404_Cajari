package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {
	private int id; // id
	private String source; // source 원본 이름 
	private String file; // 다운로드 이미지
	private boolean isImage; // 이미지 여부

} // end DTO
