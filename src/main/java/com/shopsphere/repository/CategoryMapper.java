//package com.shopsphere.repository;
//
//import com.shopsphere.entity.Category;
//import org.apache.ibatis.annotations.*;
//
//import java.util.List;
//
//@Mapper
//public interface CategoryMapper {
//
//    @Select("SELECT * FROM categories")
//    @Results(id = "categoryResultMap", value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "description", column = "description"),
//            @Result(property = "products", column = "id",
//                    many = @Many(select = "com.shopsphere.repository.ProductMapper.findByCategoryId"))
//    })
//    List<Category> findAll();
//
//    @Select("SELECT * FROM categories WHERE id = #{id}")
//    @ResultMap("categoryResultMap")
//    @ResultType(Category.class)
//    Category getCategoryById(Long id);
//
//}
