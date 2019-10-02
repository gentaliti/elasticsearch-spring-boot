package com.gentaliti.elasticsearch.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Component;

/**
 * Account
 *
 * @author Gent Aliti <alitigenti@gmail.com/>
 */
@Data
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "accounts", type = "account")
public class Account {
    @Field(type = FieldType.Text)
    @Id
    private String id;
    @Field(type = FieldType.Integer)
    @JsonProperty("account_number")
    private int accountNumber;
    @Field(type = FieldType.Integer)
    @JsonProperty("balance")
    private int balance;
    @Field(type = FieldType.Text)
    @JsonProperty("first_name")
    private String firstName;
    @Field(type = FieldType.Text)
    @JsonProperty("last_name")
    private String lastName;
    @Field(type = FieldType.Text)
    @JsonProperty("address")
    private String address;
    @Field(type = FieldType.Text)
    @JsonProperty("email")
    private String email;
    @Field(type = FieldType.Text)
    @JsonProperty("city")
    private String city;
    @Field(type = FieldType.Text)
    @JsonProperty("state")
    private String state;
    @Field(type = FieldType.Integer)
    @JsonProperty("age")
    private int age;
}
