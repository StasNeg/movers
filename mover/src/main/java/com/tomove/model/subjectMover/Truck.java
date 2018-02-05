package com.tomove.model.subjectMover;


import com.tomove.model.AbstractBaseEntity;
import com.tomove.model.enums.Area;
import com.tomove.model.objectMover.Request;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Access(AccessType.FIELD)
@Table(name = "trucks")
public class Truck extends AbstractBaseEntity {
	private final static long globalTruckMovesPerDayLimit = 3;
	
    @NotBlank
    @Column(name = "truck_type", nullable = false)
    private String truckType;

    @NotBlank
    @Column(name = "driver_id", nullable = false)
    private String driver_id;


    @Column(name = "rooms_count", nullable = false)
    @Digits(integer =1,fraction = 1)
    @Min(1)
    @Max(7)
    private int roomsCount;

    @Column(name = "moves_day", nullable = false)
    @Digits(integer =1,fraction = 1)
    @Min(1)
    @Max(3)
    private int movesDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "area", nullable = false)
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mover_id")
    private Mover mover;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "truck")
    private List<Request> request;



    public Truck() {
    }

    public Truck(Integer id, @NotBlank String truckType, @NotBlank String driver_id, @Min(1) @Max(7) int roomsCount, @Min(1) @Max(3) int movesDay, @Min(1) @Max(3) Area area, Mover mover) {
        super(id);
        this.truckType = truckType;
        this.driver_id = driver_id;
        this.roomsCount = roomsCount;
        this.movesDay = movesDay;
        this.area = area;
        this.mover = mover;
    }

    public Truck(@NotBlank String truckType, @NotBlank String driver_id, @Min(1) @Max(7) int roomsCount, @Min(1) @Max(3) int movesDay, @Min(1) @Max(3) Area area, Mover mover) {
        this.truckType = truckType;
        this.driver_id = driver_id;
        this.roomsCount = roomsCount;
        this.movesDay = movesDay;
        this.area = area;
        this.mover = mover;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public int getMovesDay() {
        return movesDay;
    }

    public void setMovesDay(int movesDay) {
        this.movesDay = movesDay;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Mover getMover() {
        return mover;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }
    
    public static long getGlobalTruckMovesPerDayLimit() {
		return globalTruckMovesPerDayLimit;
	}
}
