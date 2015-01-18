/**
 * Created by geoHeil on 17.01.15.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

public class Saying {
    private long id;

    @Length(max = 3)
    private String content;

    public Saying() {
        // Jackson deserialization
    }

    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    @ApiModelProperty(value = "id of saying")
    public long getId() {
        return id;
    }

    @JsonProperty
    @ApiModelProperty(value = "content of saying")
    public String getContent() {
        return content;
    }
}