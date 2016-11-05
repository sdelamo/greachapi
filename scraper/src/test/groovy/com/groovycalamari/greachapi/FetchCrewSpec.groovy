package com.groovycalamari.greachapi

import com.groovycalamari.greachapi.geb.HomePage
import geb.Browser
import spock.lang.Specification

class FetchCrewSpec extends Specification {

    def "I should be able to fetch Ivan Lopez and Alberto Vilches as Greach Organizers"() {

        when:
        def browser = new Browser()
        HomePage page = browser.to HomePage
        def crew = page.crewMembers()

        then:
        crew.contains(new CrewMember(name: 'Iván López', twitter: 'https://twitter.com/ilopmar'))
        crew.contains(new CrewMember(name: 'Alberto Vilches', twitter: 'https://twitter.com/albertovilches'))
    }
}
