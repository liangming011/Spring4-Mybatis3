package com.lm.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MatrixVariableController {

    //GET /books/22;author=Tom
    @RequestMapping(value = "/book/{bookid}",method= RequestMethod.GET)
    public void show1(@PathVariable String bookid,
            @MatrixVariable String author){}

    //GET /books/;author=Tom;year=2
    @RequestMapping(value = "/book",method= RequestMethod.GET)
    public void show2(@PathVariable(required = false) String bookid,
                      @MatrixVariable(value = "author") String author,
                      @MatrixVariable(value = "year") int year){
        //author=Tom
        //year=2
    }

    //GET /books/22;a=Tom/author/33;b=4
    @RequestMapping(value = "/book/{bookid}/author/{authorid}",method= RequestMethod.GET)
    public void show3(@PathVariable String bookid,
                      @PathVariable String authorid,
                      @MatrixVariable(value = "a",pathVar = "bookid") String bookidnum,
                      @MatrixVariable(value = "b",pathVar = "authorid") String authornum){
        //a=Tom
        //b=4
    }

    // GET /books/42
    @RequestMapping(value = "/books/{bookld}", method= RequestMethod.GET)
    public void show4(@MatrixVariable(required=true, defaultValue="1") int q) {
        //q=1
    }

}
