package com.groovycalamari.greachapi

import com.groovycalamari.greachapi.geb.HomePage
import geb.Browser
import spock.lang.Specification
import sun.security.krb5.internal.Ticket

class TicketsFetcherSpec extends Specification {

    def "test different conferences tickets are fetched"() {
        when:
        def browser = new Browser()
        HomePage page = browser.to HomePage
        Set<Ticket> tickets = page.tickets()

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
