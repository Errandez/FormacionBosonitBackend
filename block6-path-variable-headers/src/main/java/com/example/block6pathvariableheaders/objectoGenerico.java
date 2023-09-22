package com.example.block6pathvariableheaders;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class objectoGenerico {
    private String body;
    private List<String> headers;
    private List<String> requestParams;
}
