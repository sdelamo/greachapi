package com.groovycalamari.greachapi

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovycalamari.gebwebbot.output.Csvable
import groovycalamari.gebwebbot.output.Plistable
import groovycalamari.gebwebbot.output.Sqliteable

@CompileStatic
@EqualsAndHashCode
@ToString(excludes = 'info')
class Ticket implements Sqliteable, Csvable, Plistable  {
    String name
    String price
    String info
    String callToActionUrl
    boolean available
}
