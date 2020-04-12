package com.bsuir.main;

import com.bsuir.algorithms.CyclicGraph;
import com.bsuir.algorithms.SearchInfo;
import com.bsuir.converter.HTMLConverter;
import com.bsuir.models.Page;
import com.bsuir.parser.Parser;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = new Main().getClass().getClassLoader();
        File pagesFile = new File(classLoader.getResource("cars-book.abah").getFile());
        List<Page> pages = Parser.parsePages(pagesFile);
        System.out.println(pages);
        HTMLConverter.createPages(pages);

        System.out.println("Is graph has cycle ? - " + CyclicGraph.checkIfThereIsACycle(pages));
        List<Page> result = SearchInfo.findPagesGlobalSearch("ford", pages);
        System.out.println("Founded pages : ");
        for(Page page : result){
            System.out.println(page.getId());
        }
    }
}
