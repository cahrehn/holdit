package com.holdit.config;

import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PepperoniHugSpotConfig extends HoldItConfig {
    
    final List<String> availableSlots = List.of("5", "6", "7", "8", "9");
    final int maxCapacity = 10;
    final ZoneId timZoneId = ZoneId.of("America/New_York");

}
