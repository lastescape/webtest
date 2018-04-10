package com.qdh.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qdh on 2018/4/10.
 */
@Controller
public class QdhmvcController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String resultOutput(@RequestParam("e") String input) {
        return input.toLowerCase();
    }
}
