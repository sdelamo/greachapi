package com.groovycalamari.greachapi

import geb.Browser
import spock.lang.Specification

class FetchCrewSpec extends Specification {

    def "I should be able to fetch Ivan Lopez and Alberto Vilches as Greach Organizers"() {

        when:
        def browser = new Browser()
        browser.baseUrl = 'http://2017.greachconf.com'
        browser.go '/'
        def links = browser.$('footer a')
        def twitterLinks = links.findAll { it.getAttribute('href').contains('twitter.com') }
        def crew = twitterLinks.collect { new CrewMember(name: it.text(), twitter: it.getAttribute('href')) } as Set<CrewMember>

        then:
        crew.contains(new CrewMember(name: 'Iván López', twitter: 'https://twitter.com/ilopmar'))
        crew.contains(new CrewMember(name: 'Alberto Vilches', twitter: 'https://twitter.com/albertovilches'))
    }
}
