package com.groovycalamari.greachapi

import com.groovycalamari.greachapi.geb.TicketsFetcher
import spock.lang.Specification

class TicketsFetcherSpec extends Specification {

    def "test different conferences tickets are fetched"() {
        when:
        Set<Ticket> tickets = TicketsFetcher.fetchTickets()

        then:
        tickets
        tickets.size() == 3
        tickets.find { it.available }
        tickets.findAll { it.available }.size() == 1
        tickets.each {
            assert it.name
            assert it.info
            assert it.price
        }
    }
}
