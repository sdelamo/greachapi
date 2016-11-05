package com.groovycalamari.greachapi.geb

import com.groovycalamari.greachapi.CrewMember
import geb.Page

class HomePage extends Page {
    static url = '/'

    static content = {
        crewLinks(required: false) { $('footer a') }
    }

    Set<CrewMember> crewMembers() {
        if ( crewLinks.empty ) {
            return new HashSet<CrewMember>()
        }
        def twitterLinks = crewLinks.findAll { it.getAttribute('href').contains('twitter.com') }
        twitterLinks.collect { new CrewMember(name: it.text(), twitter: it.getAttribute('href')) } as Set<CrewMember>
    }
}
