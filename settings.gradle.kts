plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "enterparkticket"

include(
    "enterpark-ticket-admin",
    "enterpark-ticket-apis",
    "enterpark-ticket-application",
    "enterpark-ticket-apis:creator",
    "enterpark-ticket-apis:enduser",
    "enterpark-ticket-batch",
    "enterpark-ticket-domain",
    "enterpark-ticket-infra",
)
