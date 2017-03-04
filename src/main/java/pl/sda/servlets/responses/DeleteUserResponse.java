package pl.sda.servlets.responses;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by m.losK on 2017-03-04.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeleteUserResponse {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
