/**
 *  Clever Presence
 *
 *  Author: Brian Steere
 *  Date: 2014-04-18
 */
 // for the UI
 metadata {
 	definition (name: "webPresence", namespace: "dianoga", author: "Brian Steere", oauth: true) {
 		capability "Presence Sensor"

 		command "away"
 		command "present"
 	}

 	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		standardTile("presence", "device.presence", width: 2, height: 2, canChangeBackground: true) {
			state "present", label: "Present", labelIcon:"st.presence.tile.present", backgroundColor:"#53a7c0", action: "away"
			state "not present", label: "Not Present", labelIcon:"st.presence.tile.not-present", backgroundColor:"#ffffff", action: "present"
		}
        
		main "presence"
		details(["presence"])
	}
}

mappings {
	path("/present") {
		action: [ GET: "present" ]
	}

	path("/away") {
		action: [ GET: "away" ]
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"

}

// handle commands
def away() {
	log.debug "Executing 'away'"
	sendEvent(name: 'presence', value: 'not present')
}

def present() {
	log.debug "Executing 'present'"
	sendEvent(name: 'presence', value: 'present')
}
