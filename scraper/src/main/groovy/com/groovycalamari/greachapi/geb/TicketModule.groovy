package com.groovycalamari.greachapi.geb

import geb.Module

class TicketModule extends Module {

    static content = {
        planDiv(required: false) { $('div.ptp-plan', 0) }
        priceDiv(required: false) { $('div.ptp-price', 0) }
        legendDivs(required: false) { $('div.ptp-bullet-item') }
        callToActionLink(required: false) { $('div.ptp-cta a', 0) }
    }

    String name() {
        if ( planDiv.empty ) {
            return ''
        }

        planDiv.text()
    }

    String price() {
        if ( priceDiv.empty ) {
            return ''
        }

        priceDiv.text()
    }

    boolean available() {
        callToActionUrl()
    }

    String callToActionUrl() {
        if ( callToActionLink.empty ) {
            return null
        }
        def href = callToActionLink.getAttribute('href')
        if ( href == 'javascript:;' ) {
            return null
        }
        href
     }

    String info() {
        if ( legendDivs.empty ) {
            return ''
        }
        legendDivs*.text().join('</br>')
    }

}
