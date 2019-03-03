package com.taotao.manager.pojo.qo;

import java.util.ArrayList;
import java.util.List;

public class ContentQo extends PageQo {
    private Long categoryId;

    private String ids;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getIds() {
        String[] split = ids.split(",");
        List<Long> result= new ArrayList();
        for (String id : split) {
            result.add(Long.parseLong(id));
        }
        return result;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "ContentQo{" +
                "categoryId=" + categoryId +
                ", ids='" + ids + '\'' +
                '}';
    }
}
