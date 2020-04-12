package com.bsuir.parser;

import com.bsuir.models.Page;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {

    public static List<Page> parsePages(File file){
        List<Page> result = new LinkedList<>();
        try {
            Scanner myReader = new Scanner(file);
            Page newPage = null;
            Map<String, Page> mapIdAndPage = new HashMap<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains("[s.page]")){
                    newPage = new Page();
                } else if(data.contains("[id]") && newPage != null){
                    String[] elements = data.split(":");
                    newPage.setId(elements[1]);
                } else if(data.contains("[name]") && newPage != null){
                    String[] elements = data.split(":");
                    newPage.setName(elements[1]);
                } else if(data.contains("[description]") && newPage != null){
                    String[] elements = data.split(":");
                    newPage.setDescription(elements[1]);
                } else if(data.contains("[link]") && newPage != null){
                    String[] elements = data.split(":");
                    newPage.getListOfLinks().add(elements[1]);
                } else if(data.contains("[title]") && newPage != null){
                    String[] elements = data.split(":");
                    newPage.setTitle(elements[1]);
                } else if(data.contains("[e.page]") && newPage != null){
                    result.add(newPage);
                    mapIdAndPage.put(newPage.getId(), newPage);
                }

            }
            for(Page page : result){
                if(!page.getListOfLinks().isEmpty()){
                    for(String link : page.getListOfLinks()){
                        Page linkedPage = mapIdAndPage.get(link);
                        if(linkedPage != null){
                            page.getPages().add(linkedPage);
                        }
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }


}
