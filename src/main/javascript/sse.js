var SSE = window.SSE || {};

SSE.ServerSentEvent = (function($) {

	var ServerSentEvent = {
		var uri = "";
		var eventSource;

		"init" : function(uri) {
			this.uri = uri;

			eventSource = new EventSource(this.uri);
			eventSource.addEventListener("message", this.message, false);
			eventSource.addEventListener("open", this.open, false);
			eventSource.addEventListener("error", this.error, false);
        },

        "message" = function (e) {
			document.body.innerHTML += "Received data: " + event.data + " at " + Date() + " <br>";
		},

		"open" = function (e)_{
			document.body.innerHTML += "Opened connection at " + this.uri;
		},

		"error" = function (e) {
			if (e.readyState == EventSource.CLOSED) {
    			console.error(e);
  			}
		}

	};

})(jQuery);