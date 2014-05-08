/**
 *  Web Presence Manager
 *
 *  Author: Brian Steere
 *  Date: 2014-04-18
 */

// Automatically generated. Make future change here.
definition(
    name: "Web Presence Manager",
    namespace: "dianoga",
    author: "Brian Steere",
    description: "REST API for Web Presence device",
    category: "Family",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience%402x.png",
    oauth: [displayName: "Web Presence Manager", displayLink: ""]
)

preferences {
	section("Web Presence") {
		//input "devices", "device.webPresence", title: "Which Devices"
		input "devices", "capability.presenceSensor", title: "Which Devices", multiple: true
	}
}

mappings {
	path("/devices") {
		action: [ GET: "listDevices" ]
	}

	path("/devices/:id/:command") {
		action: [ GET: "update" ]
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	// TODO: subscribe to attributes, devices, locations, etc.
}

def listDevices() {
	log.debug "Listing Devices"
	devices.collect()
}

def update() {
	log.debug "update, request: params: ${params}, devices: $devices.id"
	def device = devices.find { it.id == params.id }
	log.debug "Device: $device";

	if(params.command == 'away') {
		device.away()
	} else if (params.command == 'present') {
		device.present()
	}
}
