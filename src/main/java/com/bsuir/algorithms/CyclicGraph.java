package com.bsuir.algorithms;

import com.bsuir.models.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CyclicGraph {

    /*void dfs(int start, vector<bool> & visited, vector <int> & prev,
         const vector <vector <int> > g)
    {
        visited[start] = true;
        for (auto u : g[start])
            if (!visited[u]) {
                prev[u] = start;
                dfs(u, visited, prev, g);
            }
    }
    int main()
    {
    …
        vector <bool> visited(n + 1);
        vector <int> prev(n + 1, -1);
        dfs(start, visited, prev, g);*/

    private static boolean dfs(String start, Map<String, Boolean> visited, Map<Page, String> prev, Map<String, Page> mapIdAndPage){
        visited.put(start, true);
        Page startPage = mapIdAndPage.get(start);
        for(Page page : startPage.getPages()){
            Boolean visitedPage = visited.get(page.getId());
            if(visitedPage == null || !visitedPage){
                prev.put(page, start);
                if(dfs(page.getId(), visited, prev, mapIdAndPage)){
                    return true;
                }
            } else {
                return true;
            }

        }
        return false;
    }

    public static boolean checkIfThereIsACycle(List<Page> pages){
        if(pages.isEmpty()){
            return false;
        }
        Map<String, Page> mapIdAndPage = pages.stream().collect(Collectors.toMap(Page::getId, x->x));
        Map<String, Boolean> visited = new HashMap<>();
        Map<Page, String> prev = new HashMap<>();
        return dfs(pages.get(0).getId(), visited, prev, mapIdAndPage);
    }


}
