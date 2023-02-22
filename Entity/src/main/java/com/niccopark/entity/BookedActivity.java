package com.niccopark.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookedActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingActivityId;
	
	@ManyToOne
	private Activity activity;
	
	@ManyToOne
	private Customer customer;
	
}

//private LocalDateTime activityStartTime = LocalDateTime.now();

//activityStartTime1 = {Activities1, Act2, Act1}
//activityStartTime2 = {Activities1, Act2, Act1}





//select activityStartTime, activity from BookedActivity order by activityStartTime;
//Use DTO(activityStartTime, activity)
// Map<activityStartTime, List<Activity>>


// select activity from BookedActivity where customer=:c and activityStartTime BETWEEN 



//public List<BookedActivity> findByCustomerAndActivityStartTime(Customer c, ActivityStartTime fromDate, ActivityStartTime toDate);




