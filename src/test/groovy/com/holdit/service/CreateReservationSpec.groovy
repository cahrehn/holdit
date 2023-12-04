import com.holdit.config.HoldItConfig
import com.holdit.config.PepperoniHugSpotConfig
import com.holdit.model.ReservationCreateRequest
import com.holdit.service.CheckReservationPossible
import com.holdit.service.ReservationRepository
import com.holdit.service.CreateReservation
import com.holdit.service.CreateReservationImpl


import spock.lang.Specification

class CreateReservationSpec extends Specification {

    private final CheckReservationPossible checkReservationPossible = Stub(CheckReservationPossible)
    private final ReservationRepository reservationRepository = Mock(ReservationRepository)

    private final CreateReservation toTest = new CreateReservationImpl(checkReservationPossible, reservationRepository)

    def "if reservation not possible, return error"() {
        given:
        final String error = "error"
        final ReservationCreateRequest request = Stub()
        checkReservationPossible.apply(request) >> Optional.of(error)
        
        expect:
        error == toTest.apply(request)
    }

    def "if reservation not available, return error"() {
        given:
        final ReservationCreateRequest request = Stub()
        checkReservationPossible.apply(request) >> Optional.empty()
        reservationRepository.isReservationAvailable(request) >> false

        expect:
        "Try another time." == toTest.apply(request)
    }

    def "creates a valid reservation"() {
        given:
        final ReservationCreateRequest request = Stub()
        checkReservationPossible.apply(request) >> Optional.empty()
        reservationRepository.isReservationAvailable(request) >> true

        when:
        final String result = toTest.apply(request)

        then:
        1 * reservationRepository.create(request)
        "See you soon!" == result
    }    
}
