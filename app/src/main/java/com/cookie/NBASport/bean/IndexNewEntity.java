package com.cookie.NBASport.bean;

import java.util.List;

public class IndexNewEntity extends BaseEntity{

    private List<IndexNewItem> data;

    public List<IndexNewItem> getData() {
        return data;
    }

    public void setData(List<IndexNewItem> data) {
        this.data = data;
    }

    public class IndexNewItem {

        public String type;
        public String id;
        public String column;
        public String needUpdate;

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNeedUpdate() {
            return needUpdate;
        }

        public void setNeedUpdate(String needUpdate) {
            this.needUpdate = needUpdate;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
