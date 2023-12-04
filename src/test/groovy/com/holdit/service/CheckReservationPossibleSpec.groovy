import com.holdit.config.HoldItConfig
import com.holdit.config.PepperoniHugSpotConfig
import com.holdit.model.ReservationCreateRequest
import com.holdit.service.CheckReservationPossible
import com.holdit.service.CheckReservationPossibleImpl


import spock.lang.Specification

class CheckReservationPossibleSpec extends Specification {

    private final HoldItConfig config = Stub(HoldItConfig)
    private final CheckReservationPossible toTest = new CheckReservationPossibleImpl(config)

    def "check if reservation is possible given a restaurant configuration"() {
        given:
        final ReservationCreateRequest request = 
            new ReservationCreateRequest(partySize, reservationDate, reservationSlot, partyName, contactPhone, contactEmail)

        and:
        config.getAvailableSlots() >> List.of("5", "6", "8")
        config.getMaxCapacity() >> 5

        expect:
        expected == toTest.apply(request)

        where:
        expected                                         || partySize | reservationDate | reservationSlot | partyName | contactPhone | contactEmail
        Optional.empty()                                 || 2         | "2023-11-12"    | "6"             | ""        | ""           | ""
        Optional.of("Your party is too large")           || 6         | "2023-11-12"    | "6"             | ""        | ""           | ""
        Optional.of("We can't accept reservations at 7") || 2         | "2023-11-12"    | "7"             | ""        | ""           | ""
    }
}
