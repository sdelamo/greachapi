package com.groovycalamari.greachapi.geb

import com.groovycalamari.greachapi.About
import com.groovycalamari.greachapi.CrewMember
import com.groovycalamari.greachapi.Ticket
import geb.Page

class HomePage extends Page {
    static url = '/'

    static content = {
        footerLinks(required: false) { $('footer a') }
        addressDiv(required: false) { $('div.credits', 0) }
        ticketsDivs { $('.ptp-pricing-table .ptp-item-container') }
        ticketDiv { i -> $('.ptp-pricing-table .ptp-item-container', i).module(TicketModule) }

    }

    Object asType(Class clazz) {
        if (clazz == About) {
            return new About(address: address(), youtube: youtube(), email: email(), crew: crewMembers())
        }
        else {
            super.asType(clazz)
        }
    }

    Set<CrewMember> crewMembers() {
        if ( footerLinks.empty ) {
            return new HashSet<CrewMember>()
        }
        def twitterLinks = footerLinks.findAll { it.getAttribute('href').contains('twitter.com') }
        twitterLinks.collect { new CrewMember(name: it.text(), twitter: it.getAttribute('href')) } as Set<CrewMember>
    }

    String address() {

        if ( addressDiv.empty ) {
            return ''
        }
        String text = addressDiv.text()
        removeAdvertisingCopy(text)
    }

    String youtube() {
        if ( footerLinks.empty ) {
            return ''
        }
        footerLinks.find { it.getAttribute('href').contains('youtube') }?.getAttribute('href')
    }

    String email() {
        if ( footerLinks.empty ) {
            return ''
        }
        String href = footerLinks.find { it.getAttribute('href').contains('mailto:') }?.getAttribute('href')
        if ( href ) {
            return href.substring('mailto:'.length(), href.length())
        }
        href
    }

    static String removeAdvertisingCopy(String text) {
        final String advertisingCopy = 'Powered by'
        if ( text && text.indexOf(advertisingCopy) != -1 ) {
            text = text.substring(0, text.indexOf(advertisingCopy))
        }
        text?.trim()
    }

    Set<Ticket> tickets() {
        def result = [] as Set<Ticket>
        if ( ticketsDivs.empty ) {
            return result
        }

        for ( int i = 0; i < ticketsDivs.size();  i++) {
            def mod = ticketDiv(i)
            result << new Ticket(name: mod.name(),
                    price: mod.price(),
                    info: mod.info(),
                    callToActionUrl: mod.callToActionUrl(),
                    available: mod.available())
        }
        result
    }
}
