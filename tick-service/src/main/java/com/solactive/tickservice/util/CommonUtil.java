package com.solactive.tickservice.util;

import com.solactive.tickservice.entity.Tick;
import com.solactive.tickservice.exception.ApiException;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

public class CommonUtil {

    public static List<Tick> parseTicks(List<String> tickStrings) {
        try {
            return tickStrings
                    .stream()
                    .map(tickStr -> parseTickToEntity.apply(tickStr))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ApiException("Invalid Format", BAD_REQUEST);
        }
    }

    private static Function<String, Tick> parseTickToEntity = (tickString) -> {
        Function<String, Double> parseDouble = str -> str.isBlank() ? 0 : Double.parseDouble(str);
        Function<String, Date> parseDate = str -> new Date(Long.parseLong(str));
        Function<String, String> getTokenValue = token -> token.substring(token.indexOf('=') + 1);

        var tokens = tickString.split("\\|");
        var timestamp = getTokenValue.andThen(parseDate).apply(tokens[0]);
        var price = getTokenValue.andThen(parseDouble).apply(tokens[1]);
        var closePrice = getTokenValue.andThen(parseDouble).apply(tokens[2]);
        var currency = getTokenValue.apply(tokens[3]);
        var ric = getTokenValue.apply(tokens[4]);
        return new Tick(timestamp, price, closePrice, currency, ric);
    };


}