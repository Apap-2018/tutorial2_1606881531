package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PageController {

    @RequestMapping("/viral") //Masuk ke url
    public String index() { //nama fungsinya aja buat nanti
        return "viral"; //Return masuk ke dalam template html yang akan dirender
    }


    //Contoh: localhost:8080/challenge?name=kiki
    @RequestMapping("/challenge") //Masuk ke url
    public String challenge(@RequestParam(value = "name") String name, Model model) { // Ambil dari param "name"
        model.addAttribute("name", name); //Yang akan diambil di html nanti via ${name}
        return "challenge";
    }

    @RequestMapping(value = {"/challenge", "challenge/{name}"})
    public String challengePath(@PathVariable Optional<String> name, Model model) {
        if (name.isPresent()) {
            model.addAttribute("name", name.get());
        } else {
            model.addAttribute("name", "KB");
        }
        return "challenge";
    }

    @RequestMapping("/generator")
    public String generator(@RequestParam(value = "a", required = false, defaultValue = "0") String a
            , @RequestParam(value = "b", required = false, defaultValue = "0") String b, Model model) {

        int a_cleaned = Integer.valueOf(a);
        int b_cleaned = Integer.valueOf(b);

        model.addAttribute("abGenerator", abGenerator(a_cleaned, b_cleaned));
        model.addAttribute("a_render", a);
        model.addAttribute("b_render", b);

        return "generator";
    }

    private static String abGenerator(int a, int b) {
        StringBuilder result = new StringBuilder();
        StringBuilder hm = new StringBuilder("h");

        // M generator
        for (int i = 0; i <= a ; i++) {
            if (i == 1) continue;
            hm.append('m');
        }

        // Hmm multiplier
        for (int i = 0; i <= b; i++) {
            if (i == 1) continue;
            result.append(hm);
            result.append(" ");
        }
        result.deleteCharAt(result.length() - 1); // Trailing space delete

        return result.toString();
    }

}
