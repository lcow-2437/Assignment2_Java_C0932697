package assignment2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;

// Lombok annotations for boilerplate
@Getter
@ToString
@EqualsAndHashCode
@Builder(builderClassName = "Builder", setterPrefix = "with")
@Jacksonized
public class BlogPost {
    private final String id;
    private final String authorId;
    private final String postContent;

    // Private constructor for builder pattern, with Jackson annotations for deserialization
    private BlogPost(
            @JsonProperty("id") String id,
            @JsonProperty("authorId") String authorId,
            @JsonProperty("postContent") String postContent) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (authorId == null) {
            throw new IllegalArgumentException("AuthorId cannot be null");
        }
        this.id = id;
        this.authorId = authorId;
        this.postContent = postContent;
    }
}
