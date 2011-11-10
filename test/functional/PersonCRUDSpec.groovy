import geb.spock.GebReportingSpec

import spock.lang.*

class PersonCRUDSpec extends GebReportingSpec {
	
	def "helloworld app has started"() {
        
		when:
		go()

        then:
        title == 'Welcome to Grails'
	}
}