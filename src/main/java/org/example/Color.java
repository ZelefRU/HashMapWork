package org.example;

 enum Color {
     RED("\u001B[31m"),
     GREEN("\u001B[32m"),
     YELLOW("\u001B[33m"),
     BLUE("\u001B[34m"),
     VIOLET("\u001B[35m"),
     LIGHT_BLUE("\u001B[36m"),
     RESET("\u001B[0m"),
     RED_BG("\u001B[41m"),
     GREEN_BG("\u001B[42m"),
     YELLOW_BG("\u001B[43m"),
     BLUE_BG("\u001B[44m"),
     VIOLET_BG("\u001B[45m"),
     LIGHT_BLUE_BG("\u001B[46m");
     private final String code;

     Color(String code) {this.code = code;}
     public String getCode() {return code;}
}
