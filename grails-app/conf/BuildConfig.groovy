grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {

    def seleniumVersion = "2.8.0"
    def gebVersion = "0.6.0"

    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        mavenRepo "http://snapshots.repository.codehaus.org"
        mavenRepo "http://m2repo.spockframework.org/snapshots"
        mavenRepo "http://m2repo.spockframework.org/releases"
        mavenRepo "http://repository.codehaus.org"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion") {
            exclude "xml-apis"
        }
        test("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")
        test("org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion")

        test "org.codehaus.geb:geb-spock:$gebVersion"
    }
    plugins {
        compile ":hibernate:$grailsVersion"
        compile ":jquery:1.6.1.1"
        compile ":resources:1.0.2"

        build ":tomcat:$grailsVersion"
        build ":codenarc:0.15"

        test ":spock:0.6-SNAPSHOT"
        test ":code-coverage:1.2.4"
        test ":geb:$gebVersion"
        test ":functional-test-development:0.2"
    }
}

codenarc.reports = {
    // Each report definition is of the form:
    //    REPORT-NAME(REPORT-TYPE) {
    //        PROPERTY-NAME = PROPERTY-VALUE
    //        PROPERTY-NAME = PROPERTY-VALUE
    //    }

    MyXmlReport('xml') {                    // The report name "MyXmlReport" is user-defined; Report type is 'xml'
        outputFile = 'CodeNarc-Report.xml'  // Set the 'outputFile' property of the (XML) Report
        title = 'Sample Report'             // Set the 'title' property of the (XML) Report
    }
    MyHtmlReport('html') {                  // Report type is 'html'
        outputFile = 'CodeNarc-Report.html'
        title = 'Sample Report'
    }
}
