package com.groovycalamari.greachapi

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
class About {
    String address
    String email
    String youtube
    Set<CrewMember> crew
}
