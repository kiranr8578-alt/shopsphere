//package com.shopsphere.repository;
//
//import com.shopsphere.entity.Product;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Result;
//import org.apache.ibatis.annotations.Results;
//import org.apache.ibatis.annotations.Select;
//
//import java.util.List;
//import java.util.Set;
//
//@Mapper
//public interface ProductMapper {
//    @Select("SELECT * FROM products WHERE category_id = #{categoryId}")
//    Set<Product> findByCategoryId(Integer categoryId);
//
//    @Select("""
//    SELECT p.id AS p_id, p.name AS p_name,
//           p.description AS description,p.price AS price,p.stock AS stock,
//           c.id AS c_id, c.name AS c_name
//
//    FROM products p
//    JOIN categories c ON p.category_id = c.id
//""")
////    @Results(id = "productResultMap", value = {
////            @Result(property = "id", column = "p_id"),
////            @Result(property = "name", column = "p_name"),
////            @Result(property = "category.id", column = "c_id"),
////            @Result(property = "category.name", column = "c_name")
////    })
//    @Results({
//            @Result(property = "id", column = "p_id"),
//            @Result(property = "name", column = "p_name"),
//            @Result(property = "description", column = "description"),
//            @Result(property = "price", column = "price"),
//            @Result(property = "stock", column = "stock"),
//            @Result(property = "category.id", column = "c_id"),
//            @Result(property = "category.name", column = "c_name")
//    })
////    @Select("select * from products")
////    @Results(id="mapByProducts",value={ @Result(property = "category",column = "id",one = @One(select = "com.example.Day1.module.mapper.CategoryMapper.findById"))} )
//    List<Product> findAllProducts();
//}
//
