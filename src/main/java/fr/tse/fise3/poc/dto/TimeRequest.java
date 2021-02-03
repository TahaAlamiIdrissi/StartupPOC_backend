package fr.tse.fise3.poc.dto;

import java.time.Instant;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeRequest {
		
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;
	private Long projectId;


}