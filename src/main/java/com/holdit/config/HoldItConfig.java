package com.holdit.config;

import java.time.ZoneId;
import java.util.List;

public class HoldItConfig {
    
    final List<String> availableSlots = List.of("5", "6", "7", "8", "9");
    final int maxCapacity = 10;
    final ZoneId timeZoneId = ZoneId.of("UTC");

    public List<String> getAvailableSlots() {
        return availableSlots;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
	public ZoneId getTimeZoneId() {
		return timeZoneId;
	}   
}
