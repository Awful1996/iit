package com.bsuir.converter;

import com.bsuir.models.Page;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HTMLConverter {

    private static final String HTML_TEMPLATE_START =
            "<!DOCTYPE html>\n" +
            "<html lang=\"en\" dir=\"ltr\">\n" +
            "  <head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <title>Cars shop</title>\n" +
            "  </head>\n" +
            "  <body>\n";
    private static final String HTML_TEMPLATE_END =
            "  </body>\n" +
            "</html>";


    public static void createPages(List<Page> pages){

        for(Page page : pages){
            StringBuilder pageHTML = new StringBuilder();
            pageHTML.append(HTML_TEMPLATE_START);
            if(page.getTitle() != null) {
                pageHTML.append("<h1>Title : </h1>" + page.getTitle() + "</br>");
                pageHTML.append("\n");
            }
            if(page.getName() != null) {
                pageHTML.append("<b>Name : </b>" + page.getName() + "</br>");
                pageHTML.append("\n");
            }
            if(page.getDescription() != null) {
                pageHTML.append("<b>Description : </b>" + page.getDescription() + "</br>");
                pageHTML.append("\n");
            }
            if(!page.getPages().isEmpty()) {
                pageHTML.append("<ul>");
                for(Page pageLink : page.getPages()){
                    //<a href="page2.html">page 2</a>
                    pageHTML.append("<li><a href=\"" + pageLink.getId() + ".html\">" + pageLink.getName() + "</a></li>");
                }
                pageHTML.append("</ul>");
            }
            pageHTML.append(HTML_TEMPLATE_END);
            try {
                writeContentToFileWithName(page.getId()+ ".html", pageHTML.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static void writeContentToFileWithName(String fileName, String content)
            throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        byte[] strToBytes = content.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }


}
