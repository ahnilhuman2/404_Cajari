package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ParkingDTO {
	private int id;	// id
	private String addr; // addr
	private String weekend_begin_time;
	private String weekend_end_time;
	private String weekday_begin_time;
	private String weekday_end_time;
	private String holiday_begin_time;
	private String holiday_end_time;
	private String holiday_pay_nm;
	private String fulltime_monthly;
	private String weekend_pay_nm;
	private String saturday_pay_nm;
	private String tel; // tel
	private String parking_name;
}
