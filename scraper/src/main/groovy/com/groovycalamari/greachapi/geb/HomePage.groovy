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
    public static final String MAILTO_PREFFIX = 'mailto:'

    Object asType(Class clazz) {
        if (clazz == About) {
            return new About(address: address(), youtube: youtube(), email: email(), crew: crewMembers())
        }

        super.asType(clazz)
    }

    Set<CrewMember> crewMembers() {
        if ( footerLinks.empty ) {
            return [] as Set<CrewMember>
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
        String href = footerLinks.find { it.getAttribute('href').contains(MAILTO_PREFFIX) }?.getAttribute('href')
        if ( href ) {
            return href[MAILTO_PREFFIX.length()..-1]
        }
        href
    }

    static String removeAdvertisingCopy(String str) {
        final String advertisingCopy = ' Powered by'
        def index = str.indexOf(advertisingCopy)
        def text = str
        if ( str &&  index != -1 ) {
            text = str[0..index]
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
