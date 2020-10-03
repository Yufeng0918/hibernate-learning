package com.dbs.hibernate.mapping.recursive;

import java.util.Set;

/*
 create table CATEGORIES (
 ID bigint not null ,
 NAME varchar(15),
 CATEGORY_ID bigint,
 primary key (ID)
 );

 alter table CATEGORIES add index IDX_CATEGORY_ID (CATEGORY_ID), add constraint
 FK_CATEGORY_ID foreign key (CATEGORY_ID) references CATEGORIES (ID);
 */

public class Category {

    private Long id;

    private String name;

    private Category parentCategory;

    private Set<Category> childCategories;

    public Category(String name, Category parentCategory,
                    Set<Category> childCategories) {
        this.name = name;
        this.parentCategory = parentCategory;
        this.childCategories = childCategories;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<Category> childCategories) {
        this.childCategories = childCategories;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", parentCategory="
                + parentCategory + "]";
    }
}
