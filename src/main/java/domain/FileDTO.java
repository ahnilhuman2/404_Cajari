package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {
	private int id;   // id
	private String source;  // source 원본이름
	private String file;    // file 저장된 이름
	private boolean isImage;  // 이미지 여부

} // end DTO
