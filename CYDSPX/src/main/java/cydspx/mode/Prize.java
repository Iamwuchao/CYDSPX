package cydspx.mode;

import lombok.Data;

@Data
public class Prize {
	private int id;
	private int candidate_id;
	private String achievement;
	private int prize_year;
	private String prize_level;
}
