package com.tomove;


import com.tomove.model.enums.Area;
import com.tomove.service.AreaService;
import org.junit.Assert;
import org.junit.Test;

public class MoveApplicationsTestAreaDefine {
    @Test
    public void AreaTest() {
//Ashkelon Area = Center
        float lat = 31.664214f;
        float lng = 34.577013f;
        Assert.assertEquals(Area.CENTER, AreaService.getArea(lat, lng));
//Stredot Area = SOUTH
        lat = 31.523458f;
        lng = 34.597132f;
        Assert.assertEquals(Area.SOUTH, AreaService.getArea(lat, lng));
//Haifa Area = North
        lat = 32.773550f;
        lng = 35.011086f;
        Assert.assertEquals(Area.NORTH, AreaService.getArea(lat, lng));
//Jerusalem Area = EAST
        lat = 31.777914f;
        lng = 35.212093f;
        Assert.assertEquals(Area.EAST, AreaService.getArea(lat, lng));
    }
}
