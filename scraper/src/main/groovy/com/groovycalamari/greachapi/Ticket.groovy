package com.groovycalamari.greachapi

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@CompileStatic
@EqualsAndHashCode
@ToString(excludes = 'info')
class Ticket {
    String name
    String price
    String info
    String callToActionUrl
    boolean available
}
