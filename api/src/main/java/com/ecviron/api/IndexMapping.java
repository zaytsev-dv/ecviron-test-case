/*
 * Copyright (zaytsev_dv)
 *  *|
 *  *|
 *  *|(•.•). i'm watching for you.....
 *  *|⊂ﾉ
 *  *|
 *  *|
 */

package com.ecviron.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexMapping {

    @GetMapping("/docs")
    public String userInterface() {
        return "/index.html";
    }
}
