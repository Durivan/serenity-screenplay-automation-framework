package com.ivanduri.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("job")
    private String job;

    @JsonProperty("id")
    private String id;

    @JsonProperty("createdAt")
    private String createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) && job.equals(user.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, job);
    }
}
