package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FileDTO {
	private int id; //id
	private String source;
	private String file;
	private boolean isImage; // 이미지 여부
}
