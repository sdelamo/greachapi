package com.groovycalamari.greachapi.geb

import com.groovycalamari.greachapi.GreachWebsite
import com.groovycalamari.greachapi.Ticket
import geb.Browser
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

@CompileStatic
class TicketsFetcher {

    @CompileStatic(TypeCheckingMode.SKIP)
    static Set<Ticket> fetchTickets() {
        def browser = new Browser(baseUrl: GreachWebsite.BASEURL)
        def page = browser.to HomePage
        page.tickets()
    }
}
