import com.holdit.model.ReservationListRequest
import com.holdit.model.ReservationData
import com.holdit.service.ReservationRepository
import com.holdit.service.ListReservations


import spock.lang.Specification

class ListReservationSpec extends Specification {

    private final ReservationRepository reservationRepository = Mock(ReservationRepository)

    private final ListReservations toTest = new ListReservations(reservationRepository)

    def "gets reservations"() {
        given:
        final ReservationListRequest request = Stub()
        final List<ReservationData> reservations = List.of(Stub(ReservationData))
        reservationRepository.list(request) >> reservations
        
        expect:
        reservations == toTest.apply(request).getReservations()
    }
}
