package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface CategoryDao {
    public List<Category> findAll();
}
