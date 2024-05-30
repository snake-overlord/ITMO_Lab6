package network;

import java.io.Serializable;

public class CommandResponse implements Serializable {

    private static final long serialVersionUID = 1L;
        private String message;
        private int code;

        public CommandResponse(String message, int statusCode) {
            this.message = message;
            this.code = statusCode;
        }

        public String getMessage() {
            return message;
        }

    }
