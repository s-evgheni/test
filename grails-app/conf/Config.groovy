//Configuration file setup
def configLocations = ["classpath:${appName}-config.properties"]
def currentEnvironment = System.getProperty('grails.env')

grails.config.locations = configLocations

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.resources.adhoc.includes = ['/images/**', '/css/**', '/js/**', '/plugins/**']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    def catalinaBase = 'log4j'
    def consoleLogging = org.apache.log4j.Level.ERROR
    def devPortalLogging = org.apache.log4j.Level.INFO

    environments {
        development {
            consoleLogging = org.apache.log4j.Level.TRACE
            devPortalLogging = org.apache.log4j.Level.TRACE
        }
        production {
            consoleLogging = org.apache.log4j.Level.ERROR
            devPortalLogging = org.apache.log4j.Level.INFO
        }
    }

    appenders {
        console name: 'stdout', layout: pattern(conversionPattern: '%d %-5p [%c] %m%n')
        rollingFile name: 'stacktrace', file: "${catalinaBase}/logs/stacktrace.log", maxFileSize: "10MB", layout: pattern(conversionPattern: '%d %-5p [%c] %m%n')
        appender new org.apache.log4j.DailyRollingFileAppender(
                name: 'testAppender',
                datePattern: "'.'yyyy-MM-dd",
                fileName: "${catalinaBase}/logs/test.log",
                layout: pattern(conversionPattern: '%d [%t] %-5p %c{2} %x - %m%n')
        )
    }

    environments {
        development {
            root {
                info 'stdout', 'testAppender'
            }
            trace 'grails.app.controller', 'grails.app.service', 'grails.app.filters'
            debug 'grails.app.task'
        }
        production {
            root {
                info 'stdout', 'testAppender'
            }
        }
    }
}


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.test.auth.TestUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.test.auth.UserRole'
grails.plugin.springsecurity.authority.className = 'com.test.auth.TestRole'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                ['permitAll'],
	'/assets/**':       ['permitAll'],
	'/**/js/**':        ['permitAll'],
	'/**/css/**':       ['permitAll'],
	'/**/images/**':    ['permitAll'],
	'/**/favicon.ico':  ['permitAll'],
    '/dbconsole/**':    ['permitAll']
]

grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'com.test.auth.PersistentLogin'


grails.plugin.springsecurity.openid.domainClass = 'com.test.auth.OpenID'
