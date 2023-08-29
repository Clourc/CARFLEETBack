package com.project.carfleet.dto;

import java.util.Date;

public class ReservationsDto {
    
    
    private Long id;
    private Date start_Date;
    private Date end_Date;

    public ReservationsDto(long id, Date start_Date, Date end_Date) {
        this.id = id;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
    }

    public long getId() {
        return id;
    }
        
    public Date getStart_Date() {
        return start_Date;
    }
 
    
    public Date getEnd_Date() {
        return end_Date;
    }

   
    }
    

