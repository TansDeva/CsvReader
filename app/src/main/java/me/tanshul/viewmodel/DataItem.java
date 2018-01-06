package me.tanshul.viewmodel;

import java.util.List;

/**
 * Created by tansdeva on 28/12/17.
 */

public class DataItem {
    private String title;
    private List<Data> items;

    public DataItem(String title, List<Data> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public List<Data> getItems() {
        return items;
    }

    public static class Data {
        private String name;
        private String data;

        public Data(String name, String data) {
            this.name = name;
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public String getData() {
            return data;
        }
    }
}
