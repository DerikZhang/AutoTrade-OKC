package com.tdg.ato.mapper;

import com.tdg.ato.model.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by DerikZhang on 2017/6/29.
 */
@Mapper
public interface TestMapper {
    @Select("select * from test where id = #{id}")
    Test selectById(@Param("id") int id);

    Test selectByName(String name);
}
