package cn.my.application.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.my.application.pojo.yuedulijie;

@Mapper
public interface yuedulijieMapper {
	
	public yuedulijie SelectById(@Param("id")Integer id);
}
