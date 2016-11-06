package com.groovycalamari.greachapi.output

import com.groovycalamari.greachapi.Ticket
import com.groovycalamari.greachapi.geb.TicketsFetcher
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

@CompileStatic
class Main {

    final static String FORMAT_CSV = 'csv'
    final static String FORMAT_PLIST = 'plist'
    final static String FORMAT_SQLITE = 'sqlite'
    public static final String CSV_DELIMITER = ';'

    @CompileStatic(TypeCheckingMode.SKIP)
    static void main(String[] args) {
        def (hasErrors, filename, outputpath, format) = validateArgs(args)
        if ( hasErrors ) {
            usage()
            return
        }

        def tickets = TicketsFetcher.fetchTickets()

        if ( format == FORMAT_CSV ) {
            Ticket.saveCollectionAsCsv(filename, outputpath, tickets, CSV_DELIMITER)

        } else if ( format == FORMAT_PLIST ) {
            Ticket.saveCollectionAsPlist(filename, outputpath, tickets)

        } else if ( format == FORMAT_SQLITE ) {
            Ticket.saveCollectionAsSQLite(filename, outputpath, tickets)

        }
    }

    static List validateArgs(String[] args) {
        if ( args.length < 2 ) {
            usage()
            return [true, '', '', '']
        }
        String filename = args[0]
        String outputpath = args[1]

        String format = 'csv'
        if ( args.length >= 3 ) {
            if ( !(args[2] in validFormats()) ) {
                usage()
                return [true, '', '', '']
            }
            format = args[2]
        }
        [false, filename, outputpath, format]
    }

    static List<String> validFormats() {
        [FORMAT_CSV, FORMAT_SQLITE, FORMAT_PLIST]
    }

    static void usage() {
        println "java -jar build/libs/output-all.jar greachapi /Users/sdelamo/Downloads/ (${validFormats().join('|')})"
    }
}