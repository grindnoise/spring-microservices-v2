package com.microservices.accounts.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountListDeserializer extends JsonDeserializer<List<AccountDto>> {

    @Override
    public List<AccountDto> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        
        if (node.isArray()) {
            // Handle array of accounts
            List<AccountDto> accounts = new ArrayList<>();
            for (JsonNode accountNode : node) {
                accounts.add(mapper.treeToValue(accountNode, AccountDto.class));
            }
            return accounts;
        } else if (node.isObject()) {
            // Handle single account object
            return Collections.singletonList(mapper.treeToValue(node, AccountDto.class));
        } else {
            return Collections.emptyList();
        }
    }
}
