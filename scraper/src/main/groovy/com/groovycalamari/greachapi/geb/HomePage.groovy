package com.groovycalamari.greachapi.geb

import com.groovycalamari.greachapi.CrewMember
import geb.Page

class HomePage extends Page {
    static url = '/'

    static content = {
        footerLinks(required: false) { $('footer a') }
        addressDiv(required: false) { $('div.credits', 0) }
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
}
